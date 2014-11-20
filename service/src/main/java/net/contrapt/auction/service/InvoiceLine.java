package net.contrapt.auction.service;

import java.math.BigDecimal;

/**
 * Represent invoice line information -- individual bids, purchase and payments
 */
public class InvoiceLine {

    private String description;

    private BigDecimal amount;

    public InvoiceLine(String description, BigDecimal amount) {
        this.description = description;
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
