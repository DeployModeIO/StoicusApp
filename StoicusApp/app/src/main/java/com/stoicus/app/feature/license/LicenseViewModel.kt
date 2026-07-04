package com.stoicus.app.feature.license

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stoicus.app.core.domain.usecase.VerifyLicenseUseCase
import com.stoicus.app.core.network.LicenseResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel para la activación de licencia Gumroad.
 */
@HiltViewModel
class LicenseViewModel @Inject constructor(
    private val verifyLicenseUseCase: VerifyLicenseUseCase
) : ViewModel() {

    data class UiState(
        val isLoading: Boolean = false,
        val licenseKey: String = "",
        val message: String? = null,
        val isSuccess: Boolean = false
    )

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    fun onLicenseKeyChange(key: String) {
        _state.update { it.copy(licenseKey = key, message = null) }
    }

    fun verify() {
        val key = _state.value.licenseKey.trim()
        if (key.isBlank()) {
            _state.update { it.copy(message = "Ingresa tu clave de licencia de Gumroad.") }
            return
        }

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, message = null) }
            when (val result = verifyLicenseUseCase(key)) {
                is LicenseResult.Valid -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isSuccess = true,
                            message = "¡Licencia activada! Bienvenido a Stoicus completo."
                        )
                    }
                }
                is LicenseResult.Invalid -> {
                    _state.update {
                        it.copy(isLoading = false, message = result.message)
                    }
                }
                is LicenseResult.Revoked -> {
                    _state.update {
                        it.copy(isLoading = false, message = result.message)
                    }
                }
                is LicenseResult.Error -> {
                    _state.update {
                        it.copy(isLoading = false, message = result.message)
                    }
                }
                LicenseResult.RateLimited -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            message = "Demasiados intentos. Espera unos segundos e inténtalo de nuevo."
                        )
                    }
                }
            }
        }
    }
}