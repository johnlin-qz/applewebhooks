package com.quizlet.hack.applewebhooks.data

import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository

interface AppleManagedSubscriptionRepository : JpaRepository<AppleManagedSubscription, Long> {
    fun findByProviderSpecifiedUserId(id: String, sort: Sort? = Sort(Sort.Direction.DESC, "lastModified"))
            : List<AppleManagedSubscription>
    fun findByUserId(id: Long, sort: Sort? = Sort(Sort.Direction.DESC, "lastModified"))
            : List<AppleManagedSubscription>
    fun findByOrderId(id: String, sort: Sort? = Sort(Sort.Direction.DESC, "lastModified"))
            : List<AppleManagedSubscription>
    fun findByTransactionId(id: String, sort: Sort? = Sort(Sort.Direction.DESC, "lastModified"))
            : List<AppleManagedSubscription>
}
