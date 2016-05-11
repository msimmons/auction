package net.contrapt.auction.model

import org.springframework.data.jpa.repository.JpaRepository

/**
 * Created by msimmons on 10/14/14.
 */
interface PaymentRepository : JpaRepository<Payment, Long>
