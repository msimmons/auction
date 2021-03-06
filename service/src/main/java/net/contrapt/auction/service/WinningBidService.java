package net.contrapt.auction.service;

import net.contrapt.auction.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

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

    public class Impl implements WinningBidService {

        @Autowired
        BidderRepository bidderRepository;

        @Autowired
        ItemRepository itemRepository;

        @Autowired
        WinningBidRepository winningBidRepository;

        @Override
        @Transactional
        public WinningBid addWinningBid(Long itemId, Long bidderId, BigDecimal amount) {
            Item item = itemRepository.findOne(itemId);
            Bidder bidder = bidderRepository.findOne(bidderId);
            WinningBid bid = new WinningBid(item, bidder, amount);
            winningBidRepository.save(bid);
            return bid;
        }

        @Override
        @Transactional
        public WinningBid removeWinningBid(Long bidId) {
            WinningBid bid = winningBidRepository.findOne(bidId);
            if ( bid == null ) return null;
            winningBidRepository.delete(bid);
            return bid;
        }

        @Override
        @Transactional
        public List<WinningBid> getBidsForBidder(Long bidderId) {
            return winningBidRepository.findByBidderId(bidderId);
        }

        @Override
        @Transactional
        public List<WinningBid> getBidsForItem(Long itemId) {
            return winningBidRepository.findByItemId(itemId);
        }

    }
}
