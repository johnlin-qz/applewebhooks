package com.quizlet.hack.applewebhooks.hello

import com.quizlet.hack.applewebhooks.WebhookService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class WelcomeController(val webhookService: WebhookService) {
    // inject via application.properties
    @Value("\${welcome.message}")
    private val message: String? = null

    private val tasks: List<String> = listOf("a", "b", "c", "d", "e", "f", "g")

    @GetMapping("/welcome")
    fun main(model: Model): String? {
        model.addAttribute("message", message)
        model.addAttribute("tasks", tasks)
        return "welcome" //view
    }

    // /hello?name=kotlin
    @GetMapping("/welcomeWithParam")
    fun mainWithParam(
            @RequestParam(name = "name", required = false, defaultValue = "") name: String?, model: Model): String? {
        model.addAttribute("message", name)
        return "welcome" //view
    }
}
