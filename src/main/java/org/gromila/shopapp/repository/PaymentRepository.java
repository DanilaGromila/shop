package org.gromila.shopapp.repository;

import org.gromila.shopapp.entity.Payment;
import org.gromila.shopapp.entity.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByIdAndOrderIdAndOrderUserId(Long id, Long orderId, Long userId);

    List<Payment> findAllByOrderIdAndOrderUserId(Long orderId, Long userId);

    @Modifying
    @Query(value = "UPDATE payments SET payment_status = 'TIMED_OUT' WHERE payment_status = 'IN_PROCESS' AND created_at < :date", nativeQuery = true)
    void updateExpiredPayments(@Param("date") LocalDateTime date);
}