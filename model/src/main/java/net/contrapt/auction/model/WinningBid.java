package net.contrapt.auction.model;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by msimmons on 10/10/14.
 */
@Entity
@Table(name = "winning_bid")
public class WinningBid extends AbstractEntity {

    @ManyToOne
    private Bidder bidder;

    @ManyToOne
    private Item item;

    @Column
    private BigDecimal amount;

    protected WinningBid() {}

    public WinningBid(Item item, Bidder bidder, BigDecimal amount) {
        this.item = item;
        this.bidder = bidder;
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Long getItemId() {
        return item.getId();
    }

    public Long getBidderId() {
        return bidder.getId();
    }

    @Override
    public String uniqueKey() {
        return bidder.uniqueKey()+":"+item.uniqueKey();
    }
}
