package net.contrapt.auction.model

import java.math.BigDecimal
import javax.persistence.Entity
import javax.persistence.Table

/**
 * Created by msimmons on 10/10/14.
 */
@Entity
@Table(name = "payment")
class Payment(val bidderId: Long, val method: String, val reference: String, val amount: BigDecimal) : AbstractEntity() {

    protected constructor() : this(0L, "", "", BigDecimal.ZERO){}

    override fun uniqueKey(): String {
        return "$bidderId:$method:$reference"
    }
}
