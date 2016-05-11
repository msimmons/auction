package net.contrapt.auction.model

import org.springframework.data.repository.CrudRepository

/**
 * Created by msimmons on 10/14/14.
 */
interface WinningBidRepository : CrudRepository<WinningBid, Long> {

    fun findByItemIdAndBidderId(itemId: Long?, bidderId: Long?): List<WinningBid>

    fun findByItemId(itemId: Long?): List<WinningBid>

    fun findByBidderId(bidderId: Long?): List<WinningBid>
}
