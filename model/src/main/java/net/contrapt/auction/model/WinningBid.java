package net.contrapt.auction.model;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by msimmons on 10/10/14.
 */
@Entity
@Table(name = "winning_bid")
public class WinningBid extends AbstractEntity {

    //@ManyToOne(fetch = FetchType.EAGER, optional = false)
    //private Bidder bidder;
    private Long bidderId;

    //@ManyToOne(fetch = FetchType.EAGER, optional = false)
    //private Item item;
    private Long itemId;

    @Column
    private BigDecimal amount;

    protected WinningBid() {}

    public WinningBid(Item item, Bidder bidder, BigDecimal amount) {
        this.itemId = item.getId();
        this.bidderId = bidder.getId();
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Long getItemId() {
        return itemId;
    }

    public Long getBidderId() {
        return bidderId;
    }

    @Override
    public String uniqueKey() {
        return bidderId+":"+itemId;
    }
}
