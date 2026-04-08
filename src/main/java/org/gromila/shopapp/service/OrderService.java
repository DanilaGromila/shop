package org.gromila.shopapp.service;

import lombok.RequiredArgsConstructor;
import org.gromila.shopapp.dto.OrderDto;
import org.gromila.shopapp.entity.Order;
import org.gromila.shopapp.mapper.OrderMapper;
import org.gromila.shopapp.repository.OrderRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public Long create(Long userId) {
        return orderRepository.create(userId);
    }

    public OrderDto findById(Long userId, Long id) {
        Order order = orderRepository.findById(userId, id);
        return orderMapper.toDto(order);
    }

    public List<OrderDto> findAll(Long userId) {
        List<Order> orders = orderRepository.findAll(userId);
        return orders.stream().map(orderMapper::toDto).toList();
    }

    public void delete(Long userId, Long id) {
        orderRepository.delete(userId, id);
    }
}
