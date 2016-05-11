package net.contrapt.auction.model

import java.math.BigDecimal

/**
 * Created by msimmons on 10/10/14.
 */
class BidderSummary(val id: Long, val name: String, totalBids: BigDecimal?, totalPayments: BigDecimal?) : AbstractModel() {

    val totalBids : BigDecimal
    val totalPayments : BigDecimal

    init {
        this.totalBids = totalBids ?: BigDecimal.ZERO
        this.totalPayments = totalPayments ?: BigDecimal.ZERO
    }
}
