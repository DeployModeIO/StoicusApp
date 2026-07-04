package com.stoicus.app.feature.admin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stoicus.app.core.data.local.dao.AdminAuditLogDao
import com.stoicus.app.core.domain.usecase.AdminLoginUseCase
import com.stoicus.app.core.domain.usecase.AdminResetUsageCounterUseCase
import com.stoicus.app.core.domain.usecase.AdminSimulateGumroadPurchaseUseCase
import com.stoicus.app.core.security.AdminAuthManager
import com.stoicus.app.core.security.SecureUsageCounter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Estado de la UI del panel admin.
 */
data class AdminUiState(
    val isAdminEnabled: Boolean = false,
    val isLoggedIn: Boolean = false,
    val remainingUses: Int = 0,
    val isLicensed: Boolean = false,
    val message: String? = null,
    val isLoading: Boolean = false
)

@HiltViewModel
class AdminViewModel @Inject constructor(
    private val adminAuthManager: AdminAuthManager,
    private val secureUsageCounter: SecureUsageCounter,
    private val adminLoginUseCase: AdminLoginUseCase,
    private val adminResetUsageCounterUseCase: AdminResetUsageCounterUseCase,
    private val adminSimulateGumroadPurchaseUseCase: AdminSimulateGumroadPurchaseUseCase,
    private val auditLogDao: AdminAuditLogDao
) : ViewModel() {

    private val _state = MutableStateFlow(AdminUiState())
    val state: StateFlow<AdminUiState> = _state.asStateFlow()

    init {
        refresh()
    }

    fun refresh() {
        _state.value = _state.value.copy(
            isAdminEnabled = adminAuthManager.isAdminPanelEnabled,
            isLoggedIn = adminAuthManager.isLoggedIn(),
            remainingUses = secureUsageCounter.remainingUses().coerceAtLeast(0)
        )
    }

    fun login(pin: String) {
        val success = adminLoginUseCase(pin)
        _state.value = _state.value.copy(
            isLoggedIn = success,
            message = if (success) "Acceso concedido." else "PIN incorrecto."
        )
    }

    fun logout() {
        adminAuthManager.logout()
        _state.value = _state.value.copy(isLoggedIn = false, message = "Sesión cerrada.")
    }

    fun resetUsageCounter() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            val ok = adminResetUsageCounterUseCase()
            refresh()
            _state.value = _state.value.copy(
                isLoading = false,
                message = if (ok) "Contador reseteado a 0." else "No autorizado para resetear."
            )
        }
    }

    fun simulatePurchase() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            val ok = adminSimulateGumroadPurchaseUseCase()
            _state.value = _state.value.copy(
                isLoading = false,
                isLicensed = ok,
                message = if (ok) "Licencia simulada inyectada." else "No autorizado."
            )
        }
    }

    fun clearMessage() {
        _state.value = _state.value.copy(message = null)
    }
}