package net.contrapt.auction.controller;

import net.contrapt.auction.service.WinningBidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by msimmons on 8/28/14.
 */
@RestController
public class WinningBidController extends AbstractController {

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
    public WinningBid saveBid(@RequestBody WinningBid bid) {
        return winningBidService.addWinningBid(bid.getItemId(), bid.getBidderId(), bid.getAmount());
    }

    @RequestMapping(value = "/bid", method = RequestMethod.DELETE)
    public WinningBid removeBid(@RequestBody WinningBid bid) {
        return winningBidService.removeWinningBid(bid.getId());
    }

}
