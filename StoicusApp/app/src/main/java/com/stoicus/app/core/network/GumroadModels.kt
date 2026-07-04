package com.stoicus.app.core.network

import com.google.gson.annotations.SerializedName

/**
 * Respuesta del endpoint `POST /v2/licenses/verify` de Gumroad.
 *
 * Docs: https://app.gumroad.com/api#post-/licenses/verify
 */
data class GumroadVerifyResponse(
    @SerializedName("success") val success: Boolean = false,
    @SerializedName("uses") val uses: Int? = null,
    @SerializedName("purchase") val purchase: GumroadPurchase? = null,
    @SerializedName("message") val message: String? = null
)

data class GumroadPurchase(
    @SerializedName("id") val id: String? = null,
    @SerializedName("email") val email: String? = null,
    @SerializedName("created_at") val createdAt: String? = null,
    @SerializedName("product_name") val productName: String? = null,
    @SerializedName("variants") val variants: String? = null,
    @SerializedName("refundable") val refundable: Boolean? = null,
    @SerializedName("subscription_terminated_at") val subscriptionTerminatedAt: String? = null,
    @SerializedName("subscription_cancelled_at") val subscriptionCancelledAt: String? = null,
    @SerializedName("subscription_failed_at") val subscriptionFailedAt: String? = null
) {
    /** `true` si la suscripción/compra fue terminada, cancelada o fallida. */
    val isRevoked: Boolean
        get() = subscriptionTerminatedAt != null ||
            subscriptionCancelledAt != null ||
            subscriptionFailedAt != null
}