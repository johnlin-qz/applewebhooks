package com.quizlet.hack.applewebhooks

import com.quizlet.hack.applewebhooks.data.AppleWebhook
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import okio.Okio
import org.springframework.stereotype.Service

@Service
class WebhookService {
    fun handle(json: String): String {
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
