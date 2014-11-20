package net.contrapt.auction.service;

import net.contrapt.auction.model.Payment;

import java.util.Set;

/**
 * Created by msimmons on 10/28/14.
 */
public interface PaymentService {

    /**
     * Add a payment for the given bidder
     */
    public Payment addPayment(Payment payment);

    /**
     * Return the invoice information for the given bidder
     */
    public Set<InvoiceLine> getInvoice(Long bidderId);

}
