package net.contrapt.auction.controller;

import net.contrapt.auction.model.WinningBid;
import net.contrapt.auction.model.WinningBidRepository;
import net.contrapt.auction.service.WinningBidService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.el.util.ReflectionUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by msimmons on 8/28/14.
 */
@RestController
public class WinningBidController extends BaseController {

    @Autowired
    WinningBidService winningBidService;

    @RequestMapping(value = "/bid", method = RequestMethod.POST)
    public WinningBid saveBid(@RequestBody Bid bid) {
        return winningBidService.addWinningBid(bid.itemId, bid.bidderId, bid.amount);
    }

    @RequestMapping(value = "/bid", method = RequestMethod.DELETE)
    public Bid removeBid(@RequestBody Bid bid) {
        log.info("Removing bid: "+bid);
        return bid;
    }

    public static class Bid {
        Long itemId;
        Long bidderId;
        BigDecimal amount;

        Bid() {}

        public Long getItemId() {
            return itemId;
        }

        public Long getBidderId() {
            return bidderId;
        }

        public BigDecimal getAmount() {
            return amount;
        }
    }
}
