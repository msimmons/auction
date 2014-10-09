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

    @Test
    public void testSomething() {
        Bidder bidder = bidderRepository.save(new Bidder("Mark Simmons"));
        System.out.println(bidder);
        Bidder savedBidder = bidderRepository.findOne(bidder.getId());
        System.out.println(savedBidder);
        savedBidder.setName("Laurie Rothstein");
        bidderRepository.save(savedBidder);
        entityManager.flush();
        System.out.println(savedBidder);
    }

    @Test
    public void testOther() {
        Assert.assertFalse(bidderRepository.findAll().iterator().hasNext());
        bidderRepository.save(new Bidder("Mark Simmons"));
        bidderRepository.save(new Bidder("Laurie Rothstein"));
        bidderRepository.save(new Bidder("Jesse Simmons"));
        Assert.assertEquals(3, bidderRepository.count());
        Assert.assertTrue(bidderRepository.exists(1L));
    }

    @Test
    public void testFindByName() {
        bidderRepository.save(new Bidder("Mark Simmons"));
        Bidder saved = bidderRepository.findByName("Mark Simmons");
        Assert.assertNotNull(saved);
        Assert.assertEquals("Mark Simmons", saved.getName());
    }
}
