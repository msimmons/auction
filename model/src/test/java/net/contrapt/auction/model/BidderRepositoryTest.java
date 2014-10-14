package net.contrapt.auction.model;

import org.junit.Assert;
import net.contrapt.auction.model.config.RepositoryTestConfig;
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
public class BidderRepositoryTest {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    BidderRepository bidderRepository;

    @Autowired
    WinningBidRepository winningBidRepository;

    @Autowired
    ItemRepository itemRepository;

    @Test
    public void testFindByName() {
        bidderRepository.save(new Bidder("Mark Simmons"));
        Bidder saved = bidderRepository.findByName("Mark Simmons");
        Assert.assertNotNull(saved);
        Assert.assertEquals("Mark Simmons", saved.getName());
    }

    @Test
    public void testSummary() {
        Bidder bidder = new Bidder("Mark Simmons");
        bidder.addPayment("REF1029", BigDecimal.TEN);
        bidderRepository.save(bidder);
        Item item = new Item("Apple Pie");
        itemRepository.save(item);
        winningBidRepository.save(new WinningBid(item, bidder, BigDecimal.valueOf(5)));
        entityManager.clear();
        List<BidderSummary> summaries = bidderRepository.findAllSummary();
        for ( BidderSummary summary : summaries ) {
            System.out.println(summary.getName()+" "+summary.getTotalBids()+" "+summary.getTotalPayments());
        }
    }
}