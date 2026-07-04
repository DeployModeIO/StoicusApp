package com.stoicus.app.core.domain.usecase

import com.stoicus.app.core.security.AdminAuthManager
import javax.inject.Inject

/**
 * Inicia sesión como administrador con un PIN.
 *
 * **Seguridad:**
 * - Verifica que el panel admin esté habilitado en este build ([AdminAuthManager.isAdminPanelEnabled]).
 * - Delega la verificación del PIN a PBKDF2 en [AdminAuthManager].
 * - Crea una sesión efímera con TTL de 15 min.
 *
 * @return `true` si el login fue exitoso.
 */
class AdminLoginUseCase @Inject constructor(
    private val adminAuthManager: AdminAuthManager
) {
    operator fun invoke(pin: String): Boolean {
        if (!adminAuthManager.isAdminPanelEnabled) return false
        return adminAuthManager.login(pin)
    }
}