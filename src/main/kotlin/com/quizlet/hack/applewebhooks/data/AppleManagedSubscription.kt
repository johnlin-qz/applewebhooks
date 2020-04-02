package com.quizlet.hack.applewebhooks.data

import java.time.OffsetDateTime
import javax.persistence.*

@Entity
@Table(name = "AppleManagedSubscription",
        indexes = [
                Index(name="providerSpecifiedUserId_Idx", columnList="provider_specified_user_id"),
                Index(name="userId_Idx", columnList="user_id")
        ])
class AppleManagedSubscription(
        @Column(name = "provider_specified_user_id", length = 16)
        val providerSpecifiedUserId: String,
        @Column(name = "user_id", columnDefinition = "BIGINT UNSIGNED")
        val userId: Long,
        @Column(name = "order_id", length = 16)
        val orderId: String,
        @Column(name = "transaction_id", length = 16)
        val transactionId: String,
        @Column(name = "product_id", length = 255)
        val productId: String,
        @Column(name = "start_time")

        val startTime: OffsetDateTime,
        @Column(name = "expiration_time")
        val expirationTime: OffsetDateTime,
        @Column(name = "expiration_intent", columnDefinition = "TINYINT UNSIGNED")
        val expirationIntent: Int? = 0,

        @Column(name = "cancellation_time")
        val cancellationTime: OffsetDateTime? = null,
        @Column(name = "cancellation_reason", columnDefinition = "TINYINT UNSIGNED")
        val cancellationReason: Int? = 0,

        @Column(name = "is_auto_renewing", columnDefinition = "TINYINT UNSIGNED")
        val isAutoRenewing: Int,
        @Column(name = "auto_renewing_status_change_time")
        val autoRenewingStatusChangeTime: OffsetDateTime? = null,
        @Column(name = "in_intro_offer_period", columnDefinition = "TINYINT UNSIGNED")
        val inIntroOfferPeriod: Int = 0,
        @Column(name = "in_trial_period", columnDefinition = "TINYINT UNSIGNED")
        val inTrialPeriod: Int = 0,
        @Column(name = "in_billing_retry_period", columnDefinition = "TINYINT UNSIGNED")
        val inBillingRetryPeriod: Int? = 0,

        @Column(name = "api_token", length = 8192)
        val apiToken: String,
        @Column(name = "last_modified")
        val lastModified: OffsetDateTime,

        @Column(name = "currency", length = 16)
        val currency: String? = null,
        @Column(name = "source", length = 16)
        val source: String? = null,

        @Id @GeneratedValue
        @Column(name = "id", columnDefinition = "BIGINT UNSIGNED")
        var id: Long? = 0
)
