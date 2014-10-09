package net.contrapt.auction.service;

import java.math.BigDecimal;

/**
 * Full bidder data
 */
public class Bidder extends BidderSummary {

    public Bidder(Integer id, String name, boolean winner, BigDecimal balance) {
        super(id, name, winner, balance);
    }
}
