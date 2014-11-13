package net.contrapt.auction.service;

import net.contrapt.auction.model.Payment;
import net.contrapt.auction.model.WinningBid;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by msimmons on 10/28/14.
 */
public interface PaymentService {

    /**
     * Add a payment to the system
     */
    public Payment addPayment(Long bidderId, String method, String reference, BigDecimal amount);

}
