package net.contrapt.auction.model

import java.math.BigDecimal
import java.util.*
import javax.persistence.*

/**
 * Created by msimmons on 10/10/14.
 */
@Entity
@Table(name = "item")
class Item(val name : String) : AbstractEntity() {

    @Column
    var donorName: String? = null

    @Embedded
    val donorInfo = ContactInfo()

    @Column
    var maxWinners: Int = 1

    @Column
    var minimumBid = BigDecimal.ZERO

    @OneToMany(cascade = arrayOf(CascadeType.ALL), fetch = FetchType.LAZY)
    @JoinColumn(name = "itemId", updatable = false)
    val winningBids: Set<WinningBid> = HashSet()

    constructor() : this("") {}

    override fun uniqueKey(): String {
        return name
    }
}
