package net.contrapt.auction.model

import org.springframework.data.jpa.repository.JpaRepository

/**
 * Created by msimmons on 11/21/14.
 */
interface PurchaseRepository : JpaRepository<Purchase, Long>
