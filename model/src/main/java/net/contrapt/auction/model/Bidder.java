package net.contrapt.auction.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Created by msimmons on 10/9/14.
 */
@Entity
@Table(name = "bidder")
public class Bidder extends AbstractEntity {

    private String name;

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
}
