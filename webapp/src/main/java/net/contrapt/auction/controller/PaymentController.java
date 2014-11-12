package net.contrapt.auction.controller;

import net.contrapt.auction.model.Payment;
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
public class PaymentController extends BaseController {

    @RequestMapping(value = "/payment", method = RequestMethod.POST)
    public Payment savePayment(@RequestBody PaymentData payment) {
        log.info("Got payment: "+payment);
        return new Payment(payment.bidderId, payment.method, payment.reference, payment.amount);
    }

    public static class PaymentData {
        public Long bidderId;
        public String method;
        public String reference;
        public BigDecimal amount;
    }
}
