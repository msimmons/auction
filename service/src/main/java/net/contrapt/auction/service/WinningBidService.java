package net.contrapt.auction.service;

import net.contrapt.auction.model.WinningBid;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by msimmons on 10/28/14.
 */
public interface WinningBidService {

    /**
     * Add a winning bid to the system
     */
    public WinningBid addWinningBid(Long itemId, Long bidderId, BigDecimal amount);

    /**
     * Remove a winning bid from the system
     */
    public WinningBid removeWinningBid(Long bidId);

    /**
     * Return bids for the given bidder
     */
    public List<WinningBid> getBidsForBidder(Long bidderId);

    /**
     * Return bids for the given item
     */
    public List<WinningBid> getBidsForItem(Long itemId);
}
