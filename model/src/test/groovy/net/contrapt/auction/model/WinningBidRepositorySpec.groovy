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
class WinningBidRepositorySpec extends Specification {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    BidderRepository bidderRepository;

    @Autowired
    WinningBidRepository winningBidRepository;

    @Autowired
    ItemRepository itemRepository;

    def "should find bids by item id and bidder id" () {
        given:
        def bidderName = "Mark Simmons"
        def itemName = "Apple Pie"
        def bidder = bidderRepository.save(new Bidder(bidderName))
        def item = itemRepository.save(new Item(itemName))
        def bid = winningBidRepository.save(new WinningBid(item, bidder, BigDecimal.TEN))
        entityManager.clear()

        when:
        def saved = winningBidRepository.findByItemIdAndBidderId(item.getId(), bidder.getId())

        then:
        saved.size()==1
        saved.get(0).equals(bid)
    }

    def "should find bids by bidder id" () {
        given:
        def bidderName = "Mark Simmons"
        def itemName = "Apple Pie"
        def bidder = bidderRepository.save(new Bidder(bidderName))
        def item = itemRepository.save(new Item(itemName))
        def bid = winningBidRepository.save(new WinningBid(item, bidder, BigDecimal.TEN))
        entityManager.clear()

        when:
        def saved = winningBidRepository.findByBidderId(bidder.getId())

        then:
        saved.size()==1
        saved.get(0).equals(bid)
    }

    def "should find bids by item id" () {
        given:
        def bidderName = "Mark Simmons"
        def itemName = "Apple Pie"
        def bidder = bidderRepository.save(new Bidder(bidderName))
        def item = itemRepository.save(new Item(itemName))
        def bid = winningBidRepository.save(new WinningBid(item, bidder, BigDecimal.TEN))
        entityManager.clear()

        when:
        def saved = winningBidRepository.findByItemId(item.getId())

        then:
        saved.size()==1
        saved.get(0).equals(bid)
    }
}
