package net.contrapt.auction.model;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * Created by msimmons on 10/10/14.
 */
public class BidderSummary {

    private Long id;

    private String name;

    private BigDecimal totalBids = BigDecimal.ZERO;

    private BigDecimal totalPayments = BigDecimal.ZERO;

    public BidderSummary(Long id, String name, BigDecimal totalBids, BigDecimal totalPayments) {
        this.id = id;
        this.name = name;
        this.totalBids = totalBids;
        this.totalPayments = totalPayments;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getTotalBids() {
        return totalBids;
    }

    public BigDecimal getTotalPayments() {
        return totalPayments;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
