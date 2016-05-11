package net.contrapt.auction.model

import java.math.BigDecimal
import javax.persistence.Entity
import javax.persistence.Table

/**
 * Created by msimmons on 11/11/14.
 */
@Entity
@Table(name = "purchase")
class Purchase(val bidderId : Long, val productId : Long, val amount : BigDecimal) : AbstractEntity() {


    override fun uniqueKey(): String {
        return "$bidderId:$productId:$id"
    }
}
