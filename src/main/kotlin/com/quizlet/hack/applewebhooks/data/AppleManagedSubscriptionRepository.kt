package com.quizlet.hack.applewebhooks.data

import org.springframework.data.jpa.repository.JpaRepository

interface AppleManagedSubscriptionRepository : JpaRepository<AppleManagedSubscription, Long>
