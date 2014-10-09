package net.contrapt.auction.service;

import java.util.Collection;

/**
 * Manage bidder data
 */
public interface BidderService {

    /**
     * Return a list of summarized bidder objects
     * @return
     */
    public Collection<BidderSummary> getBidders();

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
