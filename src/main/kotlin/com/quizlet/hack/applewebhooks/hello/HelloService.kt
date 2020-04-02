package com.quizlet.hack.applewebhooks.hello

import org.springframework.stereotype.Service

@Service
class HelloService {

    fun getHello(): String {
        return "hello service"
    }
}
