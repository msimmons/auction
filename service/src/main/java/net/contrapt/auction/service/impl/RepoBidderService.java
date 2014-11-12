package net.contrapt.auction.service.impl;

import net.contrapt.auction.model.Bidder;
import net.contrapt.auction.model.BidderRepository;
import net.contrapt.auction.model.BidderSummary;
import net.contrapt.auction.model.SpecificationHelper;
import net.contrapt.auction.service.BidderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by msimmons on 9/23/14.
 */
public class RepoBidderService implements BidderService {

    @Autowired
    private BidderRepository bidderRepository;

    private SpecificationHelper<Bidder> specificationHelper = new SpecificationHelper<Bidder>();

    @Override
    @Transactional
    public List<BidderSummary> getBidders() {
        return bidderRepository.findAllSummary();
    }

    @Override
    @Transactional
    public Bidder getBidder(Long bidderId) {
        return bidderRepository.findOne(specificationHelper.findByWithCollections("id", bidderId, "winningBids", "payments"));
    }

    @Override
    @Transactional
    public Bidder saveBidder(Bidder bidder) {
        return bidderRepository.save(bidder);
    }
}
