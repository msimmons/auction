package net.contrapt.auction.service.impl;

import net.contrapt.auction.model.*;
import net.contrapt.auction.service.WinningBidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * Created by msimmons on 10/28/14.
 */
public class RepoWinningBidService implements WinningBidService {

    @Autowired
    BidderRepository bidderRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    WinningBidRepository winningBidRepository;

    @Override
    @Transactional
    public WinningBid addWinningBid(Long itemId, Long bidderId, BigDecimal winningBid) {
        Item item = itemRepository.findOne(itemId);
        Bidder bidder = bidderRepository.findOne(bidderId);
        WinningBid bid = new WinningBid(item, bidder, winningBid);
        winningBidRepository.save(bid);
        return bid;
    }
}
