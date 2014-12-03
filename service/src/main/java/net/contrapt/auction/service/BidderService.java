package net.contrapt.auction.service;

import net.contrapt.auction.model.Bidder;
import net.contrapt.auction.model.BidderSummary;

import java.util.Collection;
import java.util.List;

/**
 * Manage bidder data
 */
public interface BidderService {

    /**
     * Return a list of summarized bidder objects
     * @return
     */
    public List<BidderSummary> getBidderSummaries();

    /**
     * Return full bidder object with all relationships
     * @return
     */
    public Bidder getBidder(Long bidderId);

    /**
     * Save the given bidder returning the same with any attributes updated by persistence layer
     * @param bidder
     * @return
     */
    public Bidder saveBidder(Bidder bidder);
}
