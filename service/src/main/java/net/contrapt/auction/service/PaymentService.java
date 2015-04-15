package net.contrapt.auction.service;

import net.contrapt.auction.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashSet;
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

    public class Impl implements PaymentService {

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

}
