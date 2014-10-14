package net.contrapt.auction.model;

import org.aspectj.apache.bcel.util.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by msimmons on 10/14/14.
 */
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
