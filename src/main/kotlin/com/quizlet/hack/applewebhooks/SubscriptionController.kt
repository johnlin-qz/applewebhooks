package com.quizlet.hack.applewebhooks

import com.quizlet.hack.applewebhooks.data.AppleManagedSubscriptionRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/subs")
class SubscriptionController(val repository: AppleManagedSubscriptionRepository) {
    @GetMapping("/all")
    fun getAll(@RequestParam(name = "id", required = false, defaultValue = "") id: Long?, model: Model): String? {
        model.addAttribute("subs", if (id != null) repository.getOne(id) else repository.findAll())
        return "subscriptions"
    }

    @GetMapping("/originalTransactionId/{id}")
    fun originalTransactionId(@PathVariable("id") id: String, model: Model): String? {
        model.addAttribute("subs", repository.findByProviderSpecifiedUserId(id))
        return "subscriptions"
    }

    @GetMapping("/userId/{id}")
    fun userId(@PathVariable("id") id: String, model: Model): String? {
        model.addAttribute("subs", repository.findByUserId(id.toLong()))
        return "subscriptions"
    }

    @GetMapping("/orderId/{id}")
    fun orderId(@PathVariable("id") id: String, model: Model): String? {
        model.addAttribute("subs", repository.findByOrderId(id))
        return "subscriptions"
    }

    @GetMapping("/transactionId/{id}")
    fun transactionId(@PathVariable("id") id: String, model: Model): String? {
        model.addAttribute("subs", repository.findByTransactionId(id))
        return "subscriptions"
    }
}
