package net.contrapt.auction.model;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by msimmons on 10/9/14.
 */
public interface BidderRepository extends CrudRepository<Bidder, Long> {

    public Bidder findByName(String name);
}
