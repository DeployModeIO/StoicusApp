package com.stoicus.app.core.network

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.stoicus.app.BuildConfig
import com.stoicus.app.core.security.SecurityConstants
import java.io.IOException

/**
 * Resultado de la verificación de licencia, seguro para exponer a la UI.
 */
sealed class LicenseResult {
    /** Licencia válida y activa. */
    data class Valid(val email: String?, val productName: String?) : LicenseResult()

    /** Licencia inválida o no encontrada. */
    data class Invalid(val message: String) : LicenseResult()

    /** La compra/suscripción fue revocada, cancelada o reembolsada. */
    data class Revoked(val message: String) : LicenseResult()

    /** Error de red o del servidor. */
    data class Error(val message: String, val cause: Throwable? = null) : LicenseResult()

    /** Demasiados intentos en poco tiempo (rate limit client-side). */
    data object RateLimited : LicenseResult()
}

/**
 * Repositorio de licencias Gumroad.
 *
 * Responsabilidades:
 * 1. Llamar a la API de Gumroad para verificar una licencia.
 * 2. Aplicar rate-limiting client-side para evitar abuso.
 * 3. Persistir localmente (cifrado) el estado de la licencia para uso offline.
 * 4. Exponer un método [isLicenseActive] sincrónico para el [com.stoicus.app.core.security.SecureUsageCounter].
 */
class GumroadRepository(
    private val api: GumroadApiService,
    private val context: Context
) {

    private val prefs: SharedPreferences by lazy {
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        EncryptedSharedPreferences.create(
            context,
            SecurityConstants.LICENSE_PREFS,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    /** Timestamp del último intento de verificación (para rate limiting). */
    private var lastVerifyAttempt: Long = 0L

    /**
     * Verifica una licencia contra la API de Gumroad y persiste el resultado.
     */
    suspend fun verifyLicense(licenseKey: String): LicenseResult {
        // --- Rate limiting client-side ---
        val now = System.currentTimeMillis()
        if (now - lastVerifyAttempt < SecurityConstants.LICENSE_VERIFY_MIN_INTERVAL_MS) {
            return LicenseResult.RateLimited
        }
        lastVerifyAttempt = now

        return try {
            val response = api.verifyLicense(
                productId = BuildConfig.GUMROAD_PRODUCT_ID,
                licenseKey = licenseKey.trim(),
                increment = false
            )

            if (!response.success) {
                return LicenseResult.Invalid(
                    response.message ?: "Licencia inválida o no encontrada."
                )
            }

            val purchase = response.purchase
            if (purchase?.isRevoked == true) {
                clearLicense()
                return LicenseResult.Revoked(
                    "Esta licencia fue cancelada, reembolsada o revocada."
                )
            }

            // ✅ Licencia válida: persistir localmente
            persistLicense(
                licenseKey = licenseKey.trim(),
                email = purchase?.email,
                verifiedAt = now
            )

            LicenseResult.Valid(
                email = purchase?.email,
                productName = purchase?.productName
            )
        } catch (e: retrofit2.HttpException) {
            val msg = when (e.code()) {
                401, 403 -> "Error de autenticación con Gumroad (product_id inválido)."
                404 -> "Licencia no encontrada en Gumroad."
                429 -> "Demasiados intentos. Espera unos minutos e inténtalo de nuevo."
                in 500..599 -> "Servidor de Gumroad no disponible. Intenta más tarde."
                else -> "Error de verificación (${e.code()})."
            }
            LicenseResult.Error(msg, e)
        } catch (e: IOException) {
            LicenseResult.Error(
                "Sin conexión a internet. Verifica tu red e inténtalo de nuevo.", e
            )
        } catch (e: Exception) {
            LicenseResult.Error("Error inesperado al verificar la licencia.", e)
        }
    }

    /**
     * Comprueba si hay una licencia activa persistida localmente.
     * **No llama a la red** — es para uso del SecureUsageCounter y la UI offline.
     */
    fun isLicenseActive(): Boolean =
        prefs.getBoolean(SecurityConstants.LICENSE_ACTIVE_KEY, false)

    /** Clave de licencia persistida (si la hay). */
    fun getStoredLicenseKey(): String? =
        prefs.getString(SecurityConstants.LICENSE_KEY_KEY, null)

    /** Email asociado a la licencia persistida (si lo hay). */
    fun getStoredEmail(): String? =
        prefs.getString("license_email", null)

    /** Elimina la licencia almacenada (logout admin o revocación). */
    fun clearLicense() {
        prefs.edit().clear().apply()
    }

    private fun persistLicense(
        licenseKey: String,
        email: String?,
        verifiedAt: Long
    ) {
        prefs.edit()
            .putBoolean(SecurityConstants.LICENSE_ACTIVE_KEY, true)
            .putString(SecurityConstants.LICENSE_KEY_KEY, licenseKey)
            .putString("license_email", email)
            .putLong(SecurityConstants.LICENSE_VERIFIED_AT_KEY, verifiedAt)
            .apply()
    }

    /**
     * Inyecta una licencia simulada **solo para pruebas admin**.
     * No realiza llamada a la red. Marca la app como licenciada.
     */
    fun injectSimulatedLicenseForAdmin(email: String = "admin-test@stoicus.app") {
        persistLicense(
            licenseKey = "ADMIN_SIMULATED_${System.currentTimeMillis()}",
            email = email,
            verifiedAt = System.currentTimeMillis()
        )
    }
}