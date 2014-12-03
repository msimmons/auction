package net.contrapt.auction.config;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import net.contrapt.auction.service.BidderService;
import net.contrapt.auction.service.ItemService;
import net.contrapt.auction.service.PaymentService;
import net.contrapt.auction.service.WinningBidService;
import net.contrapt.auction.service.impl.RepoBidderService;
import net.contrapt.auction.service.impl.RepoItemService;
import net.contrapt.auction.service.impl.RepoPaymentService;
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

    @Bean
    public PaymentService paymentService() {
        return new RepoPaymentService();
    }

    @Bean
    public Module customJson() {
        SimpleModule module = new SimpleModule(new Version(1,0,0,null, "net.contrapt", "auction"));
        // Add custom serializers and deserializers to the module here
        return module;
    }
}
