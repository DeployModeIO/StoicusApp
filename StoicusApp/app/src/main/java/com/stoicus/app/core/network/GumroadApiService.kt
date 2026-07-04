package com.stoicus.app.core.network

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Servicio Retrofit para la API de Licencias de Gumroad.
 *
 * Base URL: `https://api.gumroad.com/`
 * Docs: https://app.gumroad.com/api#post-/licenses/verify
 */
interface GumroadApiService {

    /**
     * Verifica una clave de licencia contra un producto específico de Gumroad.
     *
     * @param productId ID del producto en Gumroad (configurado en BuildConfig).
     * @param licenseKey Clave de licencia comprada por el usuario.
     * @param increment Si `true`, incrementa el contador de usos en el servidor de Gumroad.
     *                  Por defecto `false` para no consumir usos del servidor.
     * @return Respuesta con el estado de la licencia y datos de la compra.
     */
    @FormUrlEncoded
    @POST("v2/licenses/verify")
    suspend fun verifyLicense(
        @Field("product_id") productId: String,
        @Field("license_key") licenseKey: String,
        @Field("increment_uses_count") increment: Boolean = false
    ): GumroadVerifyResponse
}