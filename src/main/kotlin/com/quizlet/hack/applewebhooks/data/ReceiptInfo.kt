package com.quizlet.hack.applewebhooks.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReceiptInfo(
    @Json(name = "purchase_date_ms") val purchaseDate: String,
    @Json(name = "expires_date") val expiresDate: String,
    @Json(name = "transaction_id") val transactionId: String,
    @Json(name = "web_order_line_item_id") val webOrderLinItemId: String,

    @Json(name = "product_id") val productId: String,
    @Json(name = "quantity") val quantity: String,

    @Json(name = "original_purchase_date_ms") val originalPurchaseDate: String,
    @Json(name = "original_transaction_id") val originalTransactionId: String,

    @Json(name = "is_in_intro_offer_period") val isInIntroOfferPeriod: String,
    @Json(name = "is_trial_period") val isTrialPeriod: String
)
