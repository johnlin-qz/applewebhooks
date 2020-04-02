package com.quizlet.hack.applewebhooks.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PendingRenewalInfo(
    @Json(name = "auto_renew_product_id") val autoRenewProductId: String?,
    @Json(name = "auto_renew_status") val autoRenewStatus: Int,
    @Json(name = "expiration_intent") val expirationIntent: Int?,
    @Json(name = "grace_period_expires_date_ms") val gracePeriodExpiresDate: String?,
    @Json(name = "is_in_billing_retry_period") val latestReceipt: Int?,
    @Json(name = "original_transaction_id") val originalTransactionId: String,
    @Json(name = "price_consent_status") val priceConsentStatus: String?,
    @Json(name = "product_id") val productId: String
)
