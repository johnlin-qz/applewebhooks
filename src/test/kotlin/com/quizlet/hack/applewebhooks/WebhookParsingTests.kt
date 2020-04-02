package com.quizlet.hack.webhooks

import com.quizlet.hack.applewebhooks.data.AppleWebhook
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import okio.Okio
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringRunner

//import org.junit.jupiter.api.DisplayName
//import org.junit.jupiter.api.Test

@RunWith(SpringRunner::class)
class WebhookParsingTests {
    @Test
    fun foo2() {
        val expectedNotificatonType = "INTERACTIVE_RENEWAL"
        val expectedOriginalTransactionId = "490000586830879";
        val inputStream = readFileAsLinesUsingGetResourceAsStream("/webhooks/3.json")
        val moshi: Moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<AppleWebhook> = moshi.adapter(AppleWebhook::class.java)
        val webhook = adapter.fromJson(com.squareup.moshi.JsonReader.of(Okio.buffer(Okio.source(inputStream))))
        assertThat(webhook?.notificationType).isEqualTo(expectedNotificatonType)
        assertThat(webhook?.latestExpiredReceiptInfo).isNull()
        assertThat(webhook?.latestReceiptInfo).isNotNull
        assertThat(webhook?.latestReceiptInfo?.originalTransactionId).isEqualTo(expectedOriginalTransactionId)
        assertThat(webhook?.unifiedReceipt?.latestReceiptInfo).hasSize(2)
    }

    @Test
    fun foo3() {
        val expectedNotificatonType = "DID_FAIL_TO_RENEW"
        val expectedOriginalTransactionId = "390000389340128";
        val inputStream = readFileAsLinesUsingGetResourceAsStream("/webhooks/4.json")
        val moshi: Moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<AppleWebhook> = moshi.adapter(AppleWebhook::class.java)
        val webhook = adapter.fromJson(com.squareup.moshi.JsonReader.of(Okio.buffer(Okio.source(inputStream))))
        assertThat(webhook?.notificationType).isEqualTo(expectedNotificatonType)
        assertThat(webhook?.latestReceiptInfo).isNull()
        assertThat(webhook?.latestExpiredReceiptInfo).isNotNull
        assertThat(webhook?.latestExpiredReceiptInfo?.originalTransactionId).isEqualTo(expectedOriginalTransactionId)
        assertThat(webhook?.unifiedReceipt?.latestReceiptInfo).hasSize(1)
    }

    fun readFileAsLinesUsingGetResourceAsStream(fileName: String)
            = this::class.java.getResourceAsStream(fileName)
}
