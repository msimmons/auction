package net.contrapt.auction.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.el.util.ReflectionUtil;
import org.springframework.beans.BeanUtils;
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
public class BidController extends BaseController {

    @RequestMapping(value = "/bid", method = RequestMethod.POST)
    public Bid saveBid(@RequestBody Bid bid) {
        log.info("Got a bid: "+bid);
        if ( bid.getBidderId().equals(100) ) throw new IllegalArgumentException("Bidder 100 is invalid");
        return bid;
    }

    @RequestMapping(value = "/bid", method = RequestMethod.DELETE)
    public Bid removeBid(@RequestBody Bid bid) {
        log.info("Removing bid: "+bid);
        return bid;
    }

    public static class Bid {

        Integer itemId;
        Integer bidderId;
        BigDecimal amount;

        Bid() {}

        public Integer getItemId() {return itemId;}
        public Integer getBidderId() {return bidderId;}
        public BigDecimal getAmount() {return amount;}

    }
}
