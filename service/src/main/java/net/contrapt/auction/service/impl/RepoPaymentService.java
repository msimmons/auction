package net.contrapt.auction.service.impl;

import net.contrapt.auction.model.Payment;
import net.contrapt.auction.model.PaymentRepository;
import net.contrapt.auction.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * Created by msimmons on 11/13/14.
 */
public class RepoPaymentService implements PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    @Transactional
    @Override
    public Payment addPayment(Long bidderId, String method, String reference, BigDecimal amount) {
        Payment payment = new Payment(bidderId, method, reference, amount);
        return paymentRepository.save(payment);
    }

}
