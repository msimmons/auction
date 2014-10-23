package net.contrapt.auction.config;

import net.contrapt.auction.service.BidderService;
import net.contrapt.auction.service.impl.RepositoryBidderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Configuration for services
 */
@Configuration
public class ServiceConfig {

    @Bean
    public BidderService bidderService() {
        return new RepositoryBidderService();
    }
}
