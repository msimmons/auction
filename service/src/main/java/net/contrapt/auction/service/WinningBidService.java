package net.contrapt.auction.service;

import net.contrapt.auction.model.WinningBid;

import java.math.BigDecimal;

/**
 * Created by msimmons on 10/28/14.
 */
public interface WinningBidService {

    /**
     * Add a winning bid to the system
     */
    public WinningBid addWinningBid(Long itemId, Long bidderId, BigDecimal winningBid);
}
