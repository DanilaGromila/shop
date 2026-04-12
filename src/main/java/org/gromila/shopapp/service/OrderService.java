package org.gromila.shopapp.service;

import lombok.RequiredArgsConstructor;
import org.gromila.shopapp.dto.OrderDto;
import org.gromila.shopapp.entity.Order;
import org.gromila.shopapp.entity.User;
import org.gromila.shopapp.mapper.OrderMapper;
import org.gromila.shopapp.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final UserService userService;

    @Transactional
    public Long create(Long userId) {
        userService.findById(userId);
        Order order = new Order();
        order.setUser(new User(userId));
        return orderRepository.save(order).getId();
    }

    public OrderDto findById(Long userId, Long id) {
        Order order = orderRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new RuntimeException("Order no found"));
        return orderMapper.toDto(order);
    }

    public List<OrderDto> findAll(Long userId) {
        List<Order> orders = orderRepository.findAllByUserId(userId);
        return orders.stream().map(orderMapper::toDto).toList();
    }

    @Transactional
    public void delete(Long userId, Long id) {
        Order order = orderRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new RuntimeException("Order no found"));
        orderRepository.delete(order);
    }
}
