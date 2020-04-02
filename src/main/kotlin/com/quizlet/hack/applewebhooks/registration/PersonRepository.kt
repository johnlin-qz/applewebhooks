package com.quizlet.hack.applewebhooks.registration

import org.springframework.data.jpa.repository.JpaRepository

interface PersonRepository : JpaRepository<Person, Long>
