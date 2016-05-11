package net.contrapt.auction.model

import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

/**
 * Created by msimmons on 10/9/14.
 */
interface BidderRepository : CrudRepository<Bidder, Long>, JpaSpecificationExecutor<Bidder> {

    fun findByName(name: String): Bidder

    fun findByContactEmail(email: String): Bidder

    @Query("""select NEW net.contrapt.auction.model.BidderSummary(bidder.id, bidder.name, sum(bid.amount), sum(payment.amount))
from Bidder bidder FETCH ALL PROPERTIES
left outer join bidder.winningBids as bid
left outer join bidder.payments as payment group by bidder""")
    fun findAllSummary(): List<BidderSummary>
}
