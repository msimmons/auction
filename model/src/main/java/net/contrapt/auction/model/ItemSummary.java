package net.contrapt.auction.model;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * Created by msimmons on 11/12/14.
 */
public class ItemSummary {

    private Long id;

    private String name;

    private Long bidCount;

    private BigDecimal bidAmount;

    public ItemSummary(Long id, String name, Long bidCount, BigDecimal bidAmount) {
        this.id = id;
        this.name = name;
        this.bidAmount = bidAmount;
        this.bidCount = bidCount;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getBidCount() {
        return bidCount;
    }

    public BigDecimal getBidAmount() {
        return bidAmount;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
