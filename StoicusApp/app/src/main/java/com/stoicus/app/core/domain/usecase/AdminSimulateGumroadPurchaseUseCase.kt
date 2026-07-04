package com.stoicus.app.core.domain.usecase

import com.stoicus.app.core.data.local.dao.AdminAuditLogDao
import com.stoicus.app.core.data.local.entity.AdminAuditLog
import com.stoicus.app.core.network.GumroadRepository
import com.stoicus.app.core.security.AdminAuthManager
import javax.inject.Inject

/**
 * Simula una compra exitosa de Gumroad sin llamar a la API.
 *
 * **Solo para pruebas de administrador.** Inyecta una licencia local ficticia
 * para probar flujos premium sin gastar claves reales.
 *
 * Registra la acción en el log de auditoría.
 */
class AdminSimulateGumroadPurchaseUseCase @Inject constructor(
    private val adminAuthManager: AdminAuthManager,
    private val gumroadRepository: GumroadRepository,
    private val auditLogDao: AdminAuditLogDao
) {
    suspend operator fun invoke(email: String? = null): Boolean {
        if (!adminAuthManager.isAdminPanelEnabled) return false
        if (!adminAuthManager.isLoggedIn()) return false

        val finalEmail = email ?: "admin-test@stoicus.app"
        gumroadRepository.injectSimulatedLicenseForAdmin(finalEmail)

        auditLogDao.insert(
            AdminAuditLog(
                actionType = ACTION_SIMULATE_PURCHASE,
                description = "Licencia Gumroad simulada para pruebas (email=$finalEmail).",
                performedAt = System.currentTimeMillis(),
                adminEmail = finalEmail
            )
        )
        return true
    }

    companion object {
        const val ACTION_SIMULATE_PURCHASE = "SIMULATE_GUMROAD_PURCHASE"
    }
}