package net.contrapt.auction.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by msimmons on 10/9/14.
 */
@Entity
@Table(name = "bidder")
public class Bidder extends AbstractEntity {

    @Column
    private String name;

    @Embedded
    private ContactInfo contact;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "bidderId", updatable = false)
    private Set<WinningBid> winningBids = new HashSet<WinningBid>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "bidderId", updatable = false)
    private Set<Payment> payments = new LinkedHashSet<Payment>();

    public static Bidder create() {
        return new Bidder();
    }

    protected Bidder() {}

    public Bidder(String name) {
        this.name = name;
        this.contact = new ContactInfo();
    }

    public Set<WinningBid> getWinningBids() {
        return Collections.unmodifiableSet(winningBids);
    }

    public Set<Payment> getPayments() {
        return Collections.unmodifiableSet(payments);
    }

    public String getName() {
        return name;
    }

    public ContactInfo getContact() {
        return contact;
    }

    @Override
    public String uniqueKey() {
        return name;
    }

    public static Specification<Bidder> eagerJoin(final String[] attributeNames) {
        return new Specification<Bidder>() {
            @Override
            public Predicate toPredicate(Root<Bidder> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                for ( String attributeName : attributeNames ) {
                    root.fetch(attributeName, JoinType.LEFT);
                }
                return null;
            }
        };
    }
}
