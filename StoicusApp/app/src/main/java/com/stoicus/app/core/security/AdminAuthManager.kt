package com.stoicus.app.core.security

import android.content.Context
import android.content.SharedPreferences
import android.util.Base64
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.stoicus.app.BuildConfig
import java.security.SecureRandom
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

/**
 * Gestor de autenticación del administrador.
 *
 * **Seguridad:**
 * - PIN nunca se almacena en claro; se guarda como `salt + hash` PBKDF2-HMAC-SHA256.
 * - Sesión efímera en memoria con TTL de 15 minutos (no persistente).
 * - Panel admin se deshabilita en build de release vía [BuildConfig.ENABLE_ADMIN_PANEL].
 *
 * **PIN por defecto en debug:** `737287` (mapeo telefónico STOIC-U).
 */
class AdminAuthManager(private val context: Context) {

    /** Estado de la sesión admin efímera. */
    private var session: AdminSession? = null

    private val prefs: SharedPreferences by lazy {
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        EncryptedSharedPreferences.create(
            context,
            SecurityConstants.ADMIN_PREFS,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    /** `true` si el panel admin está habilitado para este build. */
    val isAdminPanelEnabled: Boolean
        get() = BuildConfig.ENABLE_ADMIN_PANEL

    /**
     * Inicializa el PIN admin por defecto si no existe.
     * Solo se llama en debug/internal builds.
     */
    fun ensureDefaultAdminPinInitialized() {
        if (!isAdminPanelEnabled) return
        if (prefs.contains(KEY_PIN_HASH)) return

        // Primera ejecución en debug: establecemos PIN por defecto.
        setPin(DEFAULT_DEBUG_PIN)
    }

    /**
     * Establece o actualiza el PIN admin.
     * Genera un salt aleatorio y almacena `salt:hash` en Base64.
     */
    fun setPin(pin: String) {
        require(pin.length in MIN_PIN_LENGTH..MAX_PIN_LENGTH) {
            "PIN debe tener entre $MIN_PIN_LENGTH y $MAX_PIN_LENGTH dígitos"
        }
        val salt = ByteArray(16).also { SecureRandom().nextBytes(it) }
        val hash = pbkdf2Hash(pin, salt)
        val combined = Base64.encodeToString(salt, Base64.NO_WRAP) +
            ":" + Base64.encodeToString(hash, Base64.NO_WRAP)
        prefs.edit().putString(KEY_PIN_HASH, combined).apply()
    }

    /**
     * Verifica un PIN introducido contra el hash almacenado.
     * @return `true` si coincide.
     */
    fun verifyPin(input: String): Boolean {
        val stored = prefs.getString(KEY_PIN_HASH, null) ?: return false
        val parts = stored.split(":")
        if (parts.size != 2) return false

        return try {
            val salt = Base64.decode(parts[0], Base64.NO_WRAP)
            val expectedHash = Base64.decode(parts[1], Base64.NO_WRAP)
            val inputHash = pbkdf2Hash(input, salt)
            constantTimeEquals(inputHash, expectedHash)
        } catch (e: Exception) {
            false
        }
    }

    /**
     * Inicia sesión admin si el PIN es correcto.
     * Emite una sesión efímera en memoria con TTL.
     * @return `true` si el login fue exitoso.
     */
    fun login(pin: String): Boolean {
        if (!verifyPin(pin)) return false
        session = AdminSession(
            issuedAt = System.currentTimeMillis(),
            expiresAt = System.currentTimeMillis() + SecurityConstants.ADMIN_SESSION_TTL_MS
        )
        return true
    }

    /** Cierra la sesión admin. */
    fun logout() {
        session = null
    }

    /**
     * Comprueba si hay una sesión admin activa y válida (no expirada).
     */
    fun isLoggedIn(): Boolean {
        val s = session ?: return false
        val now = System.currentTimeMillis()
        if (now > s.expiresAt) {
            session = null
            return false
        }
        return true
    }

    /**
     * Renueva el TTL de la sesión (extiende otros 15 min desde ahora).
     */
    fun renewSession() {
        if (session != null) {
            session = session!!.copy(
                issuedAt = System.currentTimeMillis(),
                expiresAt = System.currentTimeMillis() + SecurityConstants.ADMIN_SESSION_TTL_MS
            )
        }
    }

    // -----------------------------------------------------------------------
    // Internos
    // -----------------------------------------------------------------------

    private fun pbkdf2Hash(pin: String, salt: ByteArray): ByteArray {
        val spec = PBEKeySpec(
            pin.toCharArray(),
            salt,
            SecurityConstants.ADMIN_PIN_ITERATIONS,
            SecurityConstants.ADMIN_PIN_KEY_LENGTH_BITS
        )
        val factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
        return factory.generateSecret(spec).encoded
    }

    private fun constantTimeEquals(a: ByteArray, b: ByteArray): Boolean {
        if (a.size != b.size) return false
        var result = 0
        for (i in a.indices) {
            result = result or (a[i].toInt() xor b[i].toInt())
        }
        return result == 0
    }

    private data class AdminSession(
        val issuedAt: Long,
        val expiresAt: Long
    )

    companion object {
        private const val KEY_PIN_HASH = "admin_pin_hash"

        /** PIN por defecto en debug: 737287 (mapeo telefónico STOIC-U). */
        const val DEFAULT_DEBUG_PIN = "737287"

        const val MIN_PIN_LENGTH = 4
        const val MAX_PIN_LENGTH = 8
    }
}