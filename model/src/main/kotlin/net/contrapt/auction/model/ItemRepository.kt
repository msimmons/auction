package net.contrapt.auction.model

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

/**
 * Created by msimmons on 10/14/14.
 */
interface ItemRepository : CrudRepository<Item, Long> {

    @Query(
"""select NEW net.contrapt.auction.model.ItemSummary(item.id, item.name, count(bid.id), sum(bid.amount))
from Item item FETCH ALL PROPERTIES
left outer join item.winningBids as bid
group by item"""
    )
    fun findAllSummary(): List<ItemSummary>

}
