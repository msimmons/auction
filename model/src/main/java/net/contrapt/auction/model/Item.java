package net.contrapt.auction.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by msimmons on 10/10/14.
 */
@Entity
@Table(name = "item")
public class Item extends AbstractEntity {

    @Column
    private String name;

    @Column
    private String donorName;

    @Embedded
    private ContactInfo donorInfo;

    @Column
    private Integer maxWinners;

    @Column
    private BigDecimal minimumBid;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "itemId", updatable = false)
    Set<WinningBid> winningBids = new HashSet<WinningBid>();

    protected Item() {}

    public Item(String name) {
        this.name = name;
        this.maxWinners = 1;
        this.minimumBid = BigDecimal.ZERO;
        this.donorInfo = new ContactInfo();
    }

    public String getName() {
        return name;
    }

    public Set<WinningBid> getWinningBids() {
        return Collections.unmodifiableSet(winningBids);
    }

    public Integer getMaxWinners() {
        return maxWinners;
    }

    public BigDecimal getMinimumBid() {
        return minimumBid;
    }

    public String getDonorName() {
        return donorName;
    }

    public ContactInfo getDonorInfo() {
        return donorInfo;
    }

    @Override
    public String uniqueKey() {
        return name;
    }
}
