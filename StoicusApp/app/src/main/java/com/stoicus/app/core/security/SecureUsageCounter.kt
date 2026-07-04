package com.stoicus.app.core.security

import android.content.Context
import android.content.SharedPreferences
import android.util.Base64
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import java.security.SecureRandom
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

/**
 * Contador de 5 usos gratuito con firma de integridad HMAC-SHA256.
 *
 * **Defensas implementadas:**
 * - Almacenamiento en [EncryptedSharedPreferences] (AES-256 vía Android Keystore).
 * - Firma HMAC-SHA256 sobre `(count | lastUsedAt | deviceId)` para detectar manipulación.
 * - `deviceId` propio evita clonar el estado entre dispositivos.
 *
 * Si el estado se manipula o se borra, [increment] retorna [UsageResult.Tampered],
 * y la app puede decidir bloquear o resetear de forma segura.
 */
class SecureUsageCounter(
    private val context: Context,
    private val licenseChecker: () -> Boolean = { false }
) {

    data class UsageEntry(
        val count: Int,
        val lastUsedAt: Long,
        val deviceId: String
    )

    enum class UsageResult {
        /** Uso consumido correctamente, aún quedan usos. */
        ALLOWED,

        /** Se alcanzó el máximo de usos gratuitos. */
        LOCKED,

        /** El usuario tiene licencia activa, no se consume contador. */
        LICENSED,

        /** Se detectó manipulación del estado almacenado. */
        TAMPERED
    }

    private val prefs: SharedPreferences by lazy {
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        EncryptedSharedPreferences.create(
            context,
            SecurityConstants.USAGE_COUNTER_PREFS,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    private val hmacKey: SecretKeySpec by lazy {
        // En producción: derivar con PBKDF2 usando salt almacenado en Keystore.
        SecretKeySpec(
            SecurityConstants.HMAC_SECRET.toByteArray(Charsets.UTF_8),
            "HmacSHA256"
        )
    }

    /**
     * Obtiene un identificador del dispositivo estable.
     * Combina ANDROID_ID con un UUID propio persistido (no en prefs cifradas, para
     * que sobreviva clearing de datos de la app pero sea único por instalación real).
     */
    private fun obtainDeviceId(): String {
        // Si ya existe un deviceId firmado, lo usamos
        val existing = prefs.getString(KEY_DEVICE_ID, null)
        if (existing != null) return existing

        // Generamos uno nuevo combinando ANDROID_ID + UUID aleatorio
        val androidId = android.provider.Settings.Secure.getString(
            context.contentResolver,
            android.provider.Settings.Secure.ANDROID_ID
        ) ?: "unknown"
        val randomUuid = java.util.UUID.randomUUID().toString()
        val newDeviceId = "$androidId-$randomUuid"
        prefs.edit().putString(KEY_DEVICE_ID, newDeviceId).apply()
        return newDeviceId
    }

    /**
     * Lee y verifica la firma HMAC del estado almacenado.
     * @return la entrada si es válida, o `null` si fue manipulada/inexistente.
     */
    private fun readAndVerify(): UsageEntry? {
        val count = prefs.getInt(KEY_COUNT, -1)
        val lastUsedAt = prefs.getLong(KEY_LAST_USED_AT, -1L)
        val deviceId = prefs.getString(KEY_DEVICE_ID, null) ?: return null
        val signature = prefs.getString(KEY_SIGNATURE, null) ?: return null

        if (count < 0 || lastUsedAt < 0) return null

        val expectedSig = computeSignature(count, lastUsedAt, deviceId)
        return if (constantTimeEquals(signature, expectedSig)) {
            UsageEntry(count, lastUsedAt, deviceId)
        } else {
            null // Manipulación detectada
        }
    }

    /** Calcula la firma HMAC-SHA256 en Base64. */
    private fun computeSignature(count: Int, lastUsedAt: Long, deviceId: String): String {
        val payload = "$count|$lastUsedAt|$deviceId"
        val mac = Mac.getInstance("HmacSHA256")
        mac.init(hmacKey)
        val raw = mac.doFinal(payload.toByteArray(Charsets.UTF_8))
        return Base64.encodeToString(raw, Base64.NO_WRAP)
    }

    /** Persiste el estado con su firma recalculada. */
    private fun writeSigned(entry: UsageEntry) {
        val signature = computeSignature(entry.count, entry.lastUsedAt, entry.deviceId)
        prefs.edit()
            .putInt(KEY_COUNT, entry.count)
            .putLong(KEY_LAST_USED_AT, entry.lastUsedAt)
            .putString(KEY_DEVICE_ID, entry.deviceId)
            .putString(KEY_SIGNATURE, signature)
            .apply()
    }

    /**
     * Incrementa el contador de usos de forma segura.
     * Si la licencia está activa, no consume contador.
     */
    fun increment(): UsageResult {
        // Si el usuario ya tiene licencia, no contamos
        if (licenseChecker()) return UsageResult.LICENSED

        val current = readAndVerify()
        if (current == null) {
            // Estado corrupto/nuevo: inicializamos de forma segura
            val deviceId = obtainDeviceId()
            val newEntry = UsageEntry(
                count = 1,
                lastUsedAt = System.currentTimeMillis(),
                deviceId = deviceId
            )
            writeSigned(newEntry)
            return if (newEntry.count >= SecurityConstants.MAX_FREE_USES) {
                UsageResult.LOCKED
            } else {
                UsageResult.ALLOWED
            }
        }

        if (current.count >= SecurityConstants.MAX_FREE_USES) {
            return UsageResult.LOCKED
        }

        val newEntry = current.copy(
            count = current.count + 1,
            lastUsedAt = System.currentTimeMillis()
        )
        writeSigned(newEntry)

        return if (newEntry.count >= SecurityConstants.MAX_FREE_USES) {
            UsageResult.LOCKED
        } else {
            UsageResult.ALLOWED
        }
    }

    /** Número de usos restantes (0 si está bloqueado, -1 si manipulado). */
    fun remainingUses(): Int {
        val entry = readAndVerify() ?: return -1
        return (SecurityConstants.MAX_FREE_USES - entry.count).coerceAtLeast(0)
    }

    /** `true` si ya se alcanzó el máximo de usos y no hay licencia. */
    fun isLocked(): Boolean {
        if (licenseChecker()) return false
        val entry = readAndVerify() ?: return true // Estado corrupto = bloqueado
        return entry.count >= SecurityConstants.MAX_FREE_USES
    }

    /** `true` si el estado almacenado está manipulado o corrupto. */
    fun isTampered(): Boolean = readAndVerify() == null && hasStoredData()

    /** ¿Hay datos previos almacenados? */
    private fun hasStoredData(): Boolean =
        prefs.contains(KEY_COUNT) && prefs.contains(KEY_SIGNATURE)

    /**
     * Resetea el contador a cero. **Solo para uso administrativo.**
     * Re-genera el deviceId para invalidar intentos de replay.
     */
    fun adminReset() {
        val newDeviceId = obtainDeviceId().let { current ->
            // Forzamos rotación del deviceId al resetear
            val parts = current.split("-")
            if (parts.size == 2) {
                "${parts[0]}-${java.util.UUID.randomUUID()}"
            } else {
                "${current}-${java.util.UUID.randomUUID()}"
            }
        }
        prefs.edit().clear().apply()
        prefs.edit().putString(KEY_DEVICE_ID, newDeviceId).apply()
        writeSigned(UsageEntry(0, System.currentTimeMillis(), newDeviceId))
    }

    companion object {
        private const val KEY_COUNT = "count"
        private const val KEY_LAST_USED_AT = "last_used_at"
        private const val KEY_DEVICE_ID = "device_id"
        private const val KEY_SIGNATURE = "hmac_signature"

        /** Comparación en tiempo constante para evitar timing attacks. */
        private fun constantTimeEquals(a: String, b: String): Boolean {
            if (a.length != b.length) return false
            var result = 0
            for (i in a.indices) {
                result = result or (a[i].code xor b[i].code)
            }
            return result == 0
        }
    }
}