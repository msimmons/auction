package net.contrapt.auction.controller;

import net.contrapt.auction.model.Bidder;
import net.contrapt.auction.model.BidderSummary;
import net.contrapt.auction.model.WinningBid;
import net.contrapt.auction.service.BidderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by msimmons on 8/28/14.
 */
@RestController
public class BidderController extends BaseController {

    @Autowired
    BidderService bidderService;

    @RequestMapping(value = "/bidder/{bidderId}", method = RequestMethod.GET)
    public Bidder get(@PathVariable(value = "bidderId") Long bidderId) {
        Bidder bidder = bidderId < 0 ? Bidder.create() : bidderService.getBidder(bidderId);
        if ( bidder == null ) throw new IllegalArgumentException("No bidder found with id "+bidderId);
        return bidder;
    }

    @RequestMapping(value="/bidder", method = RequestMethod.GET)
    public Collection<BidderSummary> query() {
        Collection<BidderSummary> bidders = bidderService.getBidders();
        return bidders;
    }

    @RequestMapping(value = "/bidder", method = RequestMethod.POST)
    public Bidder save(@RequestBody Bidder bidder) {
        return bidderService.saveBidder(bidder);
    }

}
