package net.contrapt.auction.model;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

/**
 * Created by msimmons on 10/14/14.
 */
public interface WinningBidRepository extends CrudRepository<WinningBid, Long> {

    public List<WinningBid> findByItemIdAndBidderId(Long itemId, Long bidderId);

    public List<WinningBid> findByItemId(Long itemId);

    public List<WinningBid> findByBidderId(Long bidderId);
}
