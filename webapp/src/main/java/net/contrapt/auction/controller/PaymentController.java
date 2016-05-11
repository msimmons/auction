package net.contrapt.auction.controller;

import net.contrapt.auction.service.InvoiceLine;
import net.contrapt.auction.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Created by msimmons on 8/28/14.
 */
@RestController
public class PaymentController extends AbstractController {

    @Autowired
    PaymentService paymentService;

    @RequestMapping(value = "/invoice/{bidderId}", method = RequestMethod.GET)
    public Set<InvoiceLine> getInvoice(@PathVariable Long bidderId) {
        return paymentService.getInvoice(bidderId);
    }

    @RequestMapping(value = "/payment", method = RequestMethod.POST)
    public Payment savePayment(@RequestBody Payment payment) {
        return paymentService.addPayment(payment);
    }

}
