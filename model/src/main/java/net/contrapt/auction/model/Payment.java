package net.contrapt.auction.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Created by msimmons on 10/10/14.
 */
@Entity
@Table(name = "payment")
public class Payment extends AbstractEntity {

    @Column
    private String reference;

    @Column
    private BigDecimal amount;

    @ManyToOne
    private Bidder bidder;

    protected Payment() {}

    public Payment(Bidder bidder, String reference, BigDecimal amount) {
        this.bidder = bidder;
        this.reference = reference;
        this.amount = amount;
    }

    @Override
    public String uniqueKey() {
        return reference;
    }
}
