package net.contrapt.auction.model

import java.math.BigDecimal
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

/**
 * Created by msimmons on 10/10/14.
 */
@Entity
@Table(name = "winning_bid")
class WinningBid(item : Item, bidder : Bidder, val amount : BigDecimal) : AbstractEntity() {

    @ManyToOne
    @JoinColumn(name = "itemId")
    val item : Item

    @ManyToOne
    @JoinColumn(name= "bidderId")
    val bidder : Bidder

    init {
        this.item = item
        this.bidder = bidder
    }

    protected constructor() : this(Item(), Bidder(), BigDecimal.ZERO){}

    override fun uniqueKey(): String {
        return "$bidder.id:$item.id"
    }
}
