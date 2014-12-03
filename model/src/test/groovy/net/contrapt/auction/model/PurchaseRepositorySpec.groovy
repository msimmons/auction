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
class PurchaseRepositorySpec extends Specification {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    PurchaseRepository purchaseRepository;

    def "Should save the thing" () {
        given:
        def name = "Tickets"
        def amount = BigDecimal.valueOf(15)
        entityManager.clear()

        expect:
        println "hello"
    }

}
