package com.quizlet.hack.applewebhooks.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UnifiedReceipt(
    @Json(name = "environment") val environment: String,
    @Json(name = "latest_receipt") val latestReceipt: String,
    @Json(name = "latest_receipt_info") val latestReceiptInfo: List<ReceiptInfo>,
    @Json(name = "pending_renewal_info") val pendingRenewalInfo: List<PendingRenewalInfo>,
    @Json(name = "status") val status: Int
)
