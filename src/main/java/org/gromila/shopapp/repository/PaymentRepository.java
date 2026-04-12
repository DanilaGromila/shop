package org.gromila.shopapp.repository;

import org.gromila.shopapp.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByIdAndOrderIdAndOrderUserId(Long id, Long orderId, Long userId);

    List<Payment> findAllByOrderIdAndOrderUserId(Long orderId, Long userId);
}