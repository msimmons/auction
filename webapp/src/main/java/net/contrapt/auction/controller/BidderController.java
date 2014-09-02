package net.contrapt.auction.controller;

import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by msimmons on 8/28/14.
 */
@RestController
public class BidderController extends BaseController {

    @RequestMapping(value = "/bidder/{bidderId}", method = RequestMethod.GET)
    public Bidder get(@PathVariable(value = "bidderId") Integer bidderId) {
        return bidders[bidderId-1];
    }

    @RequestMapping(value = "/bidder", method = RequestMethod.GET)
    public List<Bidder> query() {
        return Arrays.asList(bidders);
    }

    @RequestMapping(value = "/bidder", method = RequestMethod.POST)
    public Bidder save(@RequestBody Bidder bidder) {
        bidders[bidder.getId()-1] = bidder;
        return bidder;
    }

    private static Bidder[] bidders = new Bidder[] {
          new Bidder(1, "Mark Simmons", true, BigDecimal.valueOf(100)),
          new Bidder(2, "Jesse Simmons", false, BigDecimal.valueOf(-10)),
          new Bidder(3, "Laurie Rothstein", true, BigDecimal.valueOf(20))
    };

    private static class Bidder {
        Integer id;
        String name;
        boolean winner;
        BigDecimal balance;

        Bidder() {}

        Bidder(Integer id, String name, boolean winner, BigDecimal balance) {
            this.id = id;
            this.name =name;
            this.winner = winner;
            this.balance = balance;
        }

        public Integer getId() {return id;}
        public String getName() {return name;}
        public boolean isWinner() {return winner;}
        public BigDecimal getBalance() {return balance;}
    }
}
