package net.contrapt.auction.controller;

import net.contrapt.auction.model.WinningBid;
import net.contrapt.auction.service.WinningBidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by msimmons on 8/28/14.
 */
@RestController
public class WinningBidController extends BaseController {

    @Autowired
    WinningBidService winningBidService;

    @RequestMapping(value="/bid", method=RequestMethod.GET, params = {"itemId"})
    public List<WinningBid> findByItemId(@RequestParam(value = "itemId") Long itemId) {
        return winningBidService.getBidsForItem(itemId);
    }

    @RequestMapping(value="/bid", method=RequestMethod.GET, params = {"bidderId"})
    public List<WinningBid> findByBidderId(@RequestParam(value = "bidderId") Long bidderId) {
        return winningBidService.getBidsForBidder(bidderId);
    }

    @RequestMapping(value = "/bid", method = RequestMethod.POST)
    public WinningBid saveBid(@RequestBody WinningBidData bid) {
        return winningBidService.addWinningBid(bid.itemId, bid.bidderId, bid.amount);
    }

    @RequestMapping(value = "/bid", method = RequestMethod.DELETE)
    public WinningBid removeBid(@RequestBody WinningBidData bid) {
        return winningBidService.removeWinningBid(bid.id);
    }

    public static class WinningBidData {
        public Long id;
        public Long itemId;
        public Long bidderId;
        public BigDecimal amount;
    }
}
