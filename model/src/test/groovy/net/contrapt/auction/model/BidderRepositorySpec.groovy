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
class BidderRepositorySpec extends Specification {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    BidderRepository bidderRepository;

    @Autowired
    WinningBidRepository winningBidRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    PaymentRepository paymentRepository;

    def "should find a bidder by name" () {
        given:
        def bidderName = "Mark Simmons"
        def bidder = bidderRepository.save(new Bidder(bidderName))
        entityManager.clear()

        when:
        def saved = bidderRepository.findByName(bidderName)

        then:
        saved.getName().equals(bidderName)
        saved.equals(bidder)
    }

    def "should find a bidder by email" () {
        given: "A bidder with an email"
        def bidderName = "Mark Simmons"
        def bidderEmail = "msimmons@gmail.com"
        Bidder bidder = new Bidder(bidderName);
        bidder.getContact().email = bidderEmail;
        bidderRepository.save(bidder);
        entityManager.clear()

        when: "Retrieve bidder by email"
        Bidder saved = bidderRepository.findByContactEmail(bidderEmail);

        then: "Should be the expected bidder"
        saved != null
        saved.equals(bidder)
        saved.getContact().email.equals(bidderEmail)

    }

    def "should return the correct bidder summary" () {
        given: "save a bidder, payment, item and winning bid"
        def bidderName = "Mark Simmons"
        def payment = BigDecimal.TEN
        def bid = BigDecimal.valueOf(5)
        Bidder bidder = new Bidder(bidderName);
        bidderRepository.save(bidder);
        Item item = new Item("Apple Pie");
        itemRepository.save(item);
        winningBidRepository.save(new WinningBid(item, bidder, bid));
        paymentRepository.save(new Payment(bidder.getId(), "check", "REF1029", payment));
        entityManager.clear()

        when: "retrieve all bidder summaries"
        List<BidderSummary> summaries = bidderRepository.findAllSummary();

        then: "we get one summary whose values match expected"
        summaries.size() == 1
        BidderSummary summary = summaries.get(0)
        summary.getName().equals(bidderName)
        summary.getTotalBids().compareTo(bid) == 0
        summary.getTotalPayments().compareTo(payment) == 0

    }

    def "should return bidder summaries when there are no winning bids"() {
        given: "save a bidder without any payments or winning bids"
        def bidderName = "Mark Simmons"
        Bidder bidder = new Bidder(bidderName);
        bidderRepository.save(bidder);
        entityManager.clear()

        when: "retrieve all bidder summaries"
        List<BidderSummary> summaries = bidderRepository.findAllSummary();

        then: "we get one summary whose values match expected"
        summaries.size() == 1
        BidderSummary summary = summaries.get(0)
        summary.getName().equals(bidderName)
        summary.getTotalBids()==null
        summary.getTotalPayments()==null
    }

    def "should do an eager join to as specified by specification"() {
        given: "save some bidders, payments, item and winning bids"
        def bidderName = "Mark Simmons"
        def payment = BigDecimal.TEN
        def bid = BigDecimal.valueOf(5)
        Bidder bidder1 = bidderRepository.save(new Bidder("bidder1"))
        Bidder bidder2 = bidderRepository.save(new Bidder("bidder2"))
        Bidder bidder3 = bidderRepository.save(new Bidder("bidder3"))
        Item item = new Item("Apple Pie");
        itemRepository.save(item);
        winningBidRepository.save(new WinningBid(item, bidder1, bid));
        winningBidRepository.save(new WinningBid(item, bidder2, bid));
        winningBidRepository.save(new WinningBid(item, bidder3, bid));
        paymentRepository.save(new Payment(bidder1.getId(), "check", "REF1029", payment));
        paymentRepository.save(new Payment(bidder2.getId(), "check", "REF1029", payment));
        paymentRepository.save(new Payment(bidder3.getId(), "check", "REF1029", payment));
        entityManager.clear()
        SpecificationHelper<Bidder> specificationHelper = new SpecificationHelper<Bidder>()

        when: "retrieve all bidders"
        Iterable<Bidder> bidders = bidderRepository.findAll(specificationHelper.joinCollections("winningBids", "payments"));

        then: "check the logs to see what happens"
        for ( Bidder b : bidders.iterator() ) {
            println(b)
        }

        when: "retrieve one bidder"
        Bidder saved = bidderRepository.findOne(specificationHelper.findByWithCollections("id", bidder1.getId(), "winningBids", "payments"))

        then:
        println(saved);
    }
}
