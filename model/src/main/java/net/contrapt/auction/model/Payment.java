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
    private String method;

    @Column
    private BigDecimal amount;

    @Column
    private Long bidderId;

    protected Payment() {}

    public Payment(Long bidderId, String method, String reference, BigDecimal amount) {
        this.bidderId = bidderId;
        this.method = method;
        this.reference = reference;
        this.amount = amount;
    }

    @Override
    public String uniqueKey() {
        return bidderId+":"+method+":"+reference;
    }

    public String getReference() {
        return reference;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Long getBidderId() {
        return bidderId;
    }

    public String getMethod() {
        return method;
    }
}
