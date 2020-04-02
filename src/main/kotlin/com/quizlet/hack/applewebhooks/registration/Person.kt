package com.quizlet.hack.applewebhooks.registration

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Person(
        val firstName: String,
        val lastName: String,
        val email: String,
        @Id @GeneratedValue
        var id: Long? = 0)
