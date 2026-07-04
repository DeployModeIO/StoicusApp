package com.stoicus.app.core.domain.usecase

import com.stoicus.app.core.data.local.dao.AdminAuditLogDao
import com.stoicus.app.core.data.local.entity.AdminAuditLog
import com.stoicus.app.core.security.AdminAuthManager
import com.stoicus.app.core.security.SecureUsageCounter
import com.stoicus.app.core.security.SecurityConstants
import javax.inject.Inject

/**
 * Resetea el contador de usos gratuitos. **Solo administrador.**
 *
 * Registra la acción en [AdminAuditLogDao] para trazabilidad.
 *
 * @return `true` si el reseteo fue autorizado y ejecutado.
 */
class AdminResetUsageCounterUseCase @Inject constructor(
    private val adminAuthManager: AdminAuthManager,
    private val secureUsageCounter: SecureUsageCounter,
    private val auditLogDao: AdminAuditLogDao
) {
    suspend operator fun invoke(): Boolean {
        // Doble verificación: panel habilitado + sesión activa
        if (!adminAuthManager.isAdminPanelEnabled) return false
        if (!adminAuthManager.isLoggedIn()) return false

        secureUsageCounter.adminReset()

        auditLogDao.insert(
            AdminAuditLog(
                actionType = ACTION_RESET_COUNTER,
                description = "Contador de usos reseteado por admin.",
                performedAt = System.currentTimeMillis()
            )
        )
        return true
    }

    companion object {
        const val ACTION_RESET_COUNTER = "RESET_USAGE_COUNTER"
    }
}