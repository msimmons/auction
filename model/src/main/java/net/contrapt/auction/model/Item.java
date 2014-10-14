package net.contrapt.auction.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by msimmons on 10/10/14.
 */
@Entity
@Table(name = "item")
public class Item extends AbstractEntity {

    @Column
    private String name;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<WinningBid> winningBids = new ArrayList<WinningBid>();

    protected Item() {}

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<WinningBid> getWinningBids() {
        return winningBids;
    }

    @Override
    public String uniqueKey() {
        return name;
    }
}
