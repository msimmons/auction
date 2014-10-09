package net.contrapt.auction.controller;

import net.contrapt.auction.service.Bidder;
import net.contrapt.auction.service.BidderService;
import net.contrapt.auction.service.BidderSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by msimmons on 8/28/14.
 */
@RestController
public class BidderController extends BaseController {

    @Autowired
    BidderService bidderService;

    @RequestMapping(value = "/bidder/{bidderId}", method = RequestMethod.GET)
    public Bidder get(@PathVariable(value = "bidderId") Long bidderId) {
        return bidderService.getBidder(bidderId);
    }

    @RequestMapping(value = "/bidder", method = RequestMethod.GET)
    public Collection<BidderSummary> query() {
        return bidderService.getBidders();
    }

    @RequestMapping(value = "/bidder", method = RequestMethod.POST)
    public Bidder save(@RequestBody Bidder bidder) {
        return bidderService.saveBidder(bidder);
    }

}
