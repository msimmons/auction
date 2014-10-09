package net.contrapt.auction.service.impl;

import net.contrapt.auction.service.Bidder;
import net.contrapt.auction.service.BidderService;
import net.contrapt.auction.service.BidderSummary;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by msimmons on 9/23/14.
 */
public class BidderServiceIM implements BidderService {

    private static Bidder[] bidders = new Bidder[] {
          new Bidder(1, "Mark Simmons", true, BigDecimal.valueOf(100)),
          new Bidder(2, "Jesse Simmons", false, BigDecimal.valueOf(-10)),
          new Bidder(3, "Laurie Rothstein", true, BigDecimal.valueOf(20))
    };

    @Override
    @Transactional
    public Collection<BidderSummary> getBidders() {
        return Arrays.asList((BidderSummary[])bidders);
    }

    @Override
    @Transactional
    public Bidder getBidder(Long bidderId) {
        if ( bidderId < 0 || bidderId >= bidders.length )
            throw new IllegalArgumentException("No bidder with id "+bidderId);
        return bidders[bidderId.intValue()-1];
    }

    @Override
    @Transactional
    public Bidder saveBidder(Bidder bidder) {
        return bidder;
    }
}
