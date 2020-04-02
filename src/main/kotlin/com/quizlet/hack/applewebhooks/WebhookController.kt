package com.quizlet.hack.applewebhooks

import com.quizlet.hack.applewebhooks.data.AppleWebhook
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class WebhookController(val webhookService: WebhookService) {

    @PostMapping(name = "/webhook", consumes = ["application/json"], produces = ["application/json"])
    fun webhook(@RequestBody json: String): NotificationTypeDto {
        val notificationType: String = webhookService.handle(json)
        return NotificationTypeDto(notificationType)
    }

    @GetMapping("/renew")
    fun renew(): AppleWebhook? {
        return webhookService.renew()
    }

    @GetMapping("/expire")
    fun expire(): AppleWebhook? {
        return webhookService.expire()
    }
}
