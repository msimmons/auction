package net.contrapt.auction.service;

import java.math.BigDecimal;

/**
 * Bidder without relationships
 */
public class BidderSummary {
    private Integer id;
    private String name;
    private boolean winner;
    private BigDecimal balance;

    protected BidderSummary() {
    }

    public BidderSummary(Integer id, String name, boolean winner, BigDecimal balance) {
        this.id = id;
        this.name = name;
        this.winner = winner;
        this.balance = balance;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isWinner() {
        return winner;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
