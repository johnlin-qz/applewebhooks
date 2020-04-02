package com.quizlet.hack.applewebhooks.registration

import com.quizlet.hack.applewebhooks.hello.HelloDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class RegistrationController {
    @Autowired
    private lateinit var personRepository: PersonRepository

    @PostMapping("/registerPerson")
    fun registerPerson(
            @RequestParam("firstName") firstName: String,
            @RequestParam("lastName") lastName: String,
            @RequestParam("email") email: String): HelloDto {

        personRepository.save(Person(firstName, lastName, email))
        return HelloDto(email)
    }

    @GetMapping("/registrants")
    fun getRegistrants(): List<HelloDto> {
        val personsList = personRepository.findAll().toList()
        return personsList.map { HelloDto(it.firstName + " " + it.lastName + ": " + it.email) }
    }
}
