package net.contrapt.auction.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by msimmons on 10/23/14.
 */
@Configuration
@EnableJpaRepositories("net.contrapt.auction.model")
@EnableJpaAuditing
@EnableTransactionManagement
public class DatabaseConfig {

    @Bean
    public AuditorAware<String> auditorAware() {
        return new AuditorAware<String>() {
            @Override
            public String getCurrentAuditor() {
                return "Someone";
            }
        };
    }

}
