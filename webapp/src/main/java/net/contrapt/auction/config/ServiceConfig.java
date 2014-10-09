package net.contrapt.auction.config;

import net.contrapt.auction.service.BidderService;
import net.contrapt.auction.service.impl.BidderServiceIM;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for services
 */
@Configuration
public class ServiceConfig {

    @Bean
    public BidderService bidderService() {
        return new BidderServiceIM();
    }
}
