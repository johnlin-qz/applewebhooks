package com.quizlet.hack.applewebhooks

import com.quizlet.hack.applewebhooks.data.AppleManagedSubscription
import com.quizlet.hack.applewebhooks.data.AppleManagedSubscriptionRepository
import com.quizlet.hack.applewebhooks.data.AppleWebhook
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import okio.Okio
import org.springframework.stereotype.Service
import java.time.*


@Service
class WebhookService(val appleManagedSubscriptionRepository: AppleManagedSubscriptionRepository) {
    fun Boolean.toInt() = if (this) 1 else 0

    fun handle(json: String): String {
        val moshi: Moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<AppleWebhook> = moshi.adapter(AppleWebhook::class.java)
        val webhook: AppleWebhook? = adapter.fromJson(json)

        val originalTransactionId = webhook?.latestReceiptInfo?.originalTransactionId ?: webhook?.latestExpiredReceiptInfo?.originalTransactionId ?: "originalTransactionId"
        val webOrderLineItemId = webhook?.latestReceiptInfo?.webOrderLinItemId ?: webhook?.latestExpiredReceiptInfo?.webOrderLinItemId ?: "webOrderLineItemId"
        val transactionId = webhook?.latestReceiptInfo?.transactionId ?: webhook?.latestExpiredReceiptInfo?.transactionId ?: "transactionId"
        val productId = webhook?.latestReceiptInfo?.productId ?: webhook?.latestExpiredReceiptInfo?.productId ?: "productId"
        val apiToken = webhook?.latestReceipt ?: webhook?.latestExpiredReceipt ?: "apiToken"
        var startTime = webhook?.latestReceiptInfo?.purchaseDate ?: webhook?.latestExpiredReceiptInfo?.purchaseDate ?: "0"
        var expirationTime = webhook?.latestReceiptInfo?.expiresDate ?: webhook?.latestExpiredReceiptInfo?.expiresDate ?: "0"
        var isAutoRenewing :String? = webhook?.autoRenewStatus

        val subscription = AppleManagedSubscription(
                providerSpecifiedUserId = originalTransactionId,
                userId = 102680,
                orderId = webOrderLineItemId,
                transactionId = transactionId,
                productId = productId,

                startTime = convertEpochToOffsetDateTime(startTime.toLong()/1000),
                expirationTime = convertEpochToOffsetDateTime(expirationTime.toLong()/1000),

                isAutoRenewing = isAutoRenewing?.toBoolean()?.toInt() ?: 0,

                apiToken = apiToken,
                lastModified = OffsetDateTime.now()
        )
        appleManagedSubscriptionRepository.save(subscription)
        return webhook?.notificationType ?: "unknown"
    }

    fun convertEpochToOffsetDateTime(epochValue: Long): OffsetDateTime {
        return OffsetDateTime.of(LocalDateTime.ofEpochSecond(epochValue, 0, ZoneOffset.UTC), ZoneOffset.UTC)
    }

    fun parrot(json: String): String {
        val moshi: Moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<AppleWebhook> = moshi.adapter(AppleWebhook::class.java)
        val webhook: AppleWebhook? = adapter.fromJson(json)
        return webhook?.notificationType ?: "unknown"
    }

    fun renew(): AppleWebhook? {
        val inputStream = readFileAsLinesUsingGetResourceAsStream("/webhooks/interactive_renewal.json")
        val moshi: Moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<AppleWebhook> = moshi.adapter(AppleWebhook::class.java)
        val webhook = adapter.fromJson(com.squareup.moshi.JsonReader.of(Okio.buffer(Okio.source(inputStream))))
        return webhook
    }

    fun expire(): AppleWebhook? {
        val inputStream = readFileAsLinesUsingGetResourceAsStream("/webhooks/did_fail_to_renew.json")
        val moshi: Moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<AppleWebhook> = moshi.adapter(AppleWebhook::class.java)
        val webhook = adapter.fromJson(com.squareup.moshi.JsonReader.of(Okio.buffer(Okio.source(inputStream))))
        return webhook
    }

    fun readFileAsLinesUsingGetResourceAsStream(fileName: String)
            = this::class.java.getResourceAsStream(fileName)
}
