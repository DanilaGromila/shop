package org.gromila.shopapp.repository;

import org.gromila.shopapp.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {
    Optional<OrderDetails> findByIdAndOrderIdAndOrderUserId(Long id, Long orderId, Long userId);

    List<OrderDetails> findAllByOrderIdAndOrderUserId(Long orderId, Long userId);
}