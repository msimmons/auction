package net.contrapt.auction.model;

import net.contrapt.auction.model.config.RepositoryTestConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by msimmons on 10/9/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RepositoryTestConfig.class})
@Transactional
public class WinningBidRepositoryTest {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    WinningBidRepository winningBidRepository;

    @Autowired
    BidderRepository bidderRepository;

    @Autowired
    ItemRepository itemRepository;

    @Test
    public void simpleCrud() {
        Bidder bidder = bidderRepository.save(new Bidder("Mark Simmons"));
        Item item = itemRepository.save(new Item("Apple Pie"));
        WinningBid bid = new WinningBid(item, bidder, BigDecimal.valueOf(100.10));
        WinningBid saved = winningBidRepository.save(bid);
        entityManager.flush();
        Assert.assertEquals(bid, saved);
        entityManager.refresh(bidder);
        entityManager.refresh(item);
        Assert.assertEquals(1, bidder.getWinningBids().size());
        Assert.assertEquals(1, item.getWinningBids().size());
        entityManager.clear();

        System.out.println(bidderRepository.findAll().iterator().next().getWinningBids());
    }

}