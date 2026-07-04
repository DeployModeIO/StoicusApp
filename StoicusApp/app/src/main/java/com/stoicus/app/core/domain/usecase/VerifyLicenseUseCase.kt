package com.stoicus.app.core.domain.usecase

import com.stoicus.app.core.network.GumroadRepository
import com.stoicus.app.core.network.LicenseResult
import javax.inject.Inject

/**
 * Verifica una clave de licencia de Gumroad ingresada por el usuario.
 *
 * Flujo:
 * 1. Llama a `POST https://api.gumroad.com/v2/licenses/verify`.
 * 2. Si es válida y no está revocada, persiste localmente (cifrado).
 * 3. A partir de aquí, [SecureUsageCounter] deja de consumir usos.
 */
class VerifyLicenseUseCase @Inject constructor(
    private val gumroadRepository: GumroadRepository
) {
    suspend operator fun invoke(licenseKey: String): LicenseResult {
        return gumroadRepository.verifyLicense(licenseKey)
    }
}