package net.contrapt.auction.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Created by msimmons on 11/11/14.
 */
@Entity
@Table(name = "purchase")
public class Purchase extends AbstractEntity {

    private Long bidderId;

    private Long productId;

    private BigDecimal amount;

    @Override
    public String uniqueKey() {
        return bidderId+":"+productId+":"+getId();
    }
}
