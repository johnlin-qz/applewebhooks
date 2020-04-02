package com.quizlet.hack.applewebhooks.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AppleWebhook(
    @Json(name = "environment") val environment: String,
    @Json(name = "notification_type") val notificationType: String,
    @Json(name = "auto_renew_product_id") val productId: String,

    @Json(name = "auto_renew_status") val autoRenewStatus: String,
    @Json(name = "auto_renew_status_change_date_ms") val autoRenewChangeDate: String?,

    @Json(name = "latest_receipt") val latestReceipt: String?,
    @Json(name = "latest_receipt_info") val latestReceiptInfo: ReceiptInfo?,

    @Json(name = "expiration_intent") val expirationIntent: Int?,
    @Json(name = "latest_expired_receipt") val latestExpiredReceipt: String?,
    @Json(name = "latest_expired_receipt_info") val latestExpiredReceiptInfo: ReceiptInfo?,

    @Json(name = "unified_receipt") val unifiedReceipt: UnifiedReceipt
)
