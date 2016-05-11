package net.contrapt.auction.model

import java.util.*
import javax.persistence.*

/**
 * Created by msimmons on 10/9/14.
 */
@Entity
@Table(name = "bidder")
class Bidder(var name: String) : AbstractEntity() {

    @Embedded
    val contact = ContactInfo()

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "bidderId", updatable = false)
    val winningBids : MutableSet<WinningBid> = HashSet<WinningBid>()

    @OneToMany(fetch = FetchType.LAZY, cascade = arrayOf(CascadeType.ALL))
    @JoinColumn(name = "bidderId", updatable = false)
    val payments : MutableSet<Payment> = HashSet<Payment>()

    @OneToMany(fetch = FetchType.LAZY, cascade = arrayOf(CascadeType.ALL))
    @JoinColumn(name = "bidderId", updatable = false)
    val purchases : MutableSet<Purchase> = HashSet<Purchase>()

     constructor() : this("") {
    }

    override fun uniqueKey(): String {
        return name
    }

    companion object {

        fun create(): Bidder {
            return Bidder()
        }
    }

}
