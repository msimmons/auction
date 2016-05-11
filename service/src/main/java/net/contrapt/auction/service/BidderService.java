package net.contrapt.auction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Manage bidder data
 */
public interface BidderService {

    /**
     * Return a list of summarized bidder objects
     * @return
     */
    public List<BidderSummary> getBidderSummaries();

    /**
     * Return full bidder object with all relationships
     * @return
     */
    public Bidder getBidder(Long bidderId);

    /**
     * Save the given bidder returning the same with any attributes updated by persistence layer
     * @param bidder
     * @return
     */
    public Bidder saveBidder(Bidder bidder);

    public class Impl implements BidderService {
        @Autowired
        private BidderRepository bidderRepository;

        private SpecificationHelper<Bidder> spec = new SpecificationHelper<Bidder>();

        @Override
        @Transactional
        public List<BidderSummary> getBidderSummaries() {
            return bidderRepository.findAllSummary();
        }

        @Override
        @Transactional
        public Bidder getBidder(Long bidderId) {
            return bidderRepository.findOne(spec.findBy("id", bidderId, "winningBids", "payments"));
        }

        @Override
        @Transactional
        public Bidder saveBidder(Bidder bidder) {
            if ( bidder.getId() == null && bidderRepository.findByName(bidder.getName()) != null )
                throw new IllegalStateException("Bidder with name "+bidder.getName()+" already exists");
            return bidderRepository.save(bidder);
        }
    }
}
