package net.contrapt.auction.model

import java.math.BigDecimal

/**
 * Created by msimmons on 11/12/14.
 */
class ItemSummary(val id: Long, val name: String, val bidCount: Long, val bidAmount: BigDecimal) : AbstractModel() {
}
