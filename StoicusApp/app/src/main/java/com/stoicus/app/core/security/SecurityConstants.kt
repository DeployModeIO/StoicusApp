package com.stoicus.app.core.security

/**
 * Constantes de seguridad centralizadas.
 *
 * ⚠️ NOTA: [HMAC_SECRET] y [ADMIN_PIN_SALT] deben moverse a `local.properties` → `BuildConfig`
 * o a un backend para producción. Están aquí como valores por defecto para debug.
 */
object SecurityConstants {

    /** Máximo de usos gratuitos antes del bloqueo. */
    const val MAX_FREE_USES = 5

    /** Nombre del archivo EncryptedSharedPreferences para el contador. */
    const val USAGE_COUNTER_PREFS = "stoicus_secure_usage_prefs"

    /** Nombre del archivo EncryptedSharedPreferences para admin. */
    const val ADMIN_PREFS = "stoicus_admin_prefs"

    /** Nombre del archivo EncryptedSharedPreferences para licencia. */
    const val LICENSE_PREFS = "stoicus_license_prefs"

    // ---------------------------------------------------------------------------
    // HMAC para firma de integridad del contador (anti-tampering)
    // ---------------------------------------------------------------------------

    /**
     * Secreto HMAC para firmar el contador.
     *
     * ⚠️ En producción, combinar con `BuildConfig` + Android Keystore para derivar la clave.
     * Esto evita que un atacante simplemente copie un estado válido del contador entre apps.
     */
    const val HMAC_SECRET = "STOICUS_HMAC_v1_DO_NOT_USE_IN_PRODUCTION_2026"

    /** Iteraciones PBKDF2 para derivar la clave HMAC. */
    const val HMAC_ITERATIONS = 10_000

    /** Longitud de la clave HMAC derivada (bits). */
    const val HMAC_KEY_LENGTH_BITS = 256

    // ---------------------------------------------------------------------------
    // Admin PIN
    // ---------------------------------------------------------------------------

    /**
     * Iteraciones PBKDF2 para el hash del PIN admin.
     * 210_000 iteraciones según recomendaciones OWASP 2023 para PBKDF2-HMAC-SHA256.
     */
    const val ADMIN_PIN_ITERATIONS = 210_000

    /** Longitud del hash del PIN admin (bits). */
    const val ADMIN_PIN_KEY_LENGTH_BITS = 256

    /**
     * Salt por defecto para el PIN admin en debug.
     *
     * PIN por defecto en debug: `737287` (mapeo telefónico STOIC-U).
     * ⚠️ En release, este salt se genera aleatoriamente en la primera ejecución
     * y el PIN se establece mediante [AdminPinSetupHelper].
     */
    const val ADMIN_PIN_DEFAULT_SALT_B64 = "STOICUS_DEFAULT_SALT_DEBUG_ONLY"

    /** Duración de la sesión admin efímera (ms). 15 minutos. */
    const val ADMIN_SESSION_TTL_MS = 15L * 60 * 1000

    // ---------------------------------------------------------------------------
    // Licencia
    // ---------------------------------------------------------------------------

    /** Clave para persistir localmente el estado de la licencia. */
    const val LICENSE_ACTIVE_KEY = "license_active"
    const val LICENSE_KEY_KEY = "license_key"
    const val LICENSE_VERIFIED_AT_KEY = "license_verified_at"

    /** Intervalo mínimo entre intentos de verificación de licencia (ms) para rate limiting. */
    const val LICENSE_VERIFY_MIN_INTERVAL_MS = 5_000L
}