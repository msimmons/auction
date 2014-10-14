package net.contrapt.auction.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by msimmons on 10/9/14.
 */
public interface BidderRepository extends CrudRepository<Bidder, Long> {

    public Bidder findByName(String name);

    @Query(
          "select NEW net.contrapt.auction.model.BidderSummary(bidder.id, bidder.name, sum(bid.amount), sum(payment.amount)) " +
          "from Bidder bidder FETCH ALL PROPERTIES " +
          "left outer join bidder.winningBids as bid "+
          "left outer join bidder.payments as payment group by bidder"
    )
    public List<BidderSummary> findAllSummary();
}
