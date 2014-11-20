package net.contrapt.auction.service.impl;

import net.contrapt.auction.model.*;
import net.contrapt.auction.service.InvoiceLine;
import net.contrapt.auction.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by msimmons on 11/13/14.
 */
public class RepoPaymentService implements PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    BidderRepository bidderRepository;

    @Transactional
    @Override
    public Payment addPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Transactional
    @Override
    public Set<InvoiceLine> getInvoice(Long bidderId) {
        Bidder bidder = bidderRepository.findOne(bidderId);
        if ( bidder == null )
            throw new IllegalStateException("No bidder exists with id "+bidderId);
        Set<InvoiceLine> lines = new LinkedHashSet<InvoiceLine>();
        for ( WinningBid bid : bidder.getWinningBids() ) {
            lines.add(new InvoiceLine("Winning bid for item "+bid.getItemId(), bid.getAmount()));
        }
        for ( Payment payment : bidder.getPayments() ) {
            lines.add(new InvoiceLine("Payment "+payment.getReference()+" on "+payment.getCreatedAt(), payment.getAmount().negate()));
        }
        return lines;
    }

}
