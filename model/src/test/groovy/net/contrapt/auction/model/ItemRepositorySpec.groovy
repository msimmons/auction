package net.contrapt.auction.model

import net.contrapt.auction.model.config.RepositoryTestConfig
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

/**
 * Created by msimmons on 11/5/14.
 */
@ContextConfiguration(classes = [RepositoryTestConfig.class])
@Transactional
class ItemRepositorySpec extends Specification {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    BidderRepository bidderRepository;

    @Autowired
    WinningBidRepository winningBidRepository;

    @Autowired
    ItemRepository itemRepository;

    def "should return the correct item summary" () {
        given: "save a bidder, item and winning bid"
        def bidderName = "Mark Simmons"
        def payment = BigDecimal.TEN
        def bid = BigDecimal.valueOf(5)
        Bidder bidder = new Bidder(bidderName);
        bidderRepository.save(bidder);
        def itemName = "Apple Pie";
        Item item = new Item(itemName);
        itemRepository.save(item);
        winningBidRepository.save(new WinningBid(item, bidder, bid));
        entityManager.clear()

        when: "retrieve all item summaries"
        List<ItemSummary> summaries = itemRepository.findAllSummary();

        then: "we get one summary whose values match expected"
        summaries.size() == 1
        ItemSummary summary = summaries.get(0)
        summary.getName().equals(itemName)
        summary.getBidCount().equals(1L)
        summary.getBidAmount().compareTo(bid) == 0

    }

    def "should return item summaries when there are no winning bids"() {
        given: "save an item without any winning bids"
        def itemName = "Farm Share"
        Item item = new Item(itemName);
        itemRepository.save(item);
        entityManager.clear()

        when: "retrieve all item summaries"
        List<ItemSummary> summaries = itemRepository.findAllSummary();

        then: "we get one summary whose values match expected"
        summaries.size() == 1
        ItemSummary summary = summaries.get(0)
        summary.getName().equals(itemName)
        summary.getBidAmount()==null
        summary.getBidCount().equals(0L)
    }
}
