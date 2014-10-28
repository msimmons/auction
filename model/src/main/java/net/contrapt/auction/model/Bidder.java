package net.contrapt.auction.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by msimmons on 10/9/14.
 */
@Entity
@Table(name = "bidder")
public class Bidder extends AbstractEntity {

    @Column
    private String name;

    @OneToMany(mappedBy = "bidder", fetch = FetchType.LAZY)
    private List<WinningBid> winningBids = new ArrayList<WinningBid>();

    @OneToMany(mappedBy = "bidder", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Payment> payments = new ArrayList<Payment>();

    @Embedded
    private ContactInfo contact;

    public static Bidder create() {
        return new Bidder();
    }

    protected Bidder() {}

    public Bidder(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<WinningBid> getWinningBids() {
        return winningBids;
    }

    public void addPayment(String reference, BigDecimal amount) {
        payments.add(new Payment(this, reference, amount));
    }

    public void setContact(ContactInfo contact) {
        this.contact = contact;
    }

    public ContactInfo getContact() {
        return contact;
    }

    @Override
    public String uniqueKey() {
        return name;
    }
}
