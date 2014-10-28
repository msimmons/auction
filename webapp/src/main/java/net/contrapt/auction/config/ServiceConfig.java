package net.contrapt.auction.config;

import net.contrapt.auction.service.BidderService;
import net.contrapt.auction.service.ItemService;
import net.contrapt.auction.service.WinningBidService;
import net.contrapt.auction.service.impl.RepoBidderService;
import net.contrapt.auction.service.impl.RepoItemService;
import net.contrapt.auction.service.impl.RepoWinningBidService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for services
 */
@Configuration
public class ServiceConfig {

    @Bean
    public BidderService bidderService() {
        return new RepoBidderService();
    }

    @Bean
    public ItemService itemService() {
        return new RepoItemService();
    }

    @Bean
    public WinningBidService winningBidService() {
        return new RepoWinningBidService();
    }
}
