package org.gromila.shopapp.service;

import org.gromila.shopapp.dto.OrderDto;
import org.gromila.shopapp.entity.Order;
import org.gromila.shopapp.mapper.OrderMapper;
import org.gromila.shopapp.repository.OrderRepository;

import java.util.List;

public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    public Long create(Long userId) {
        return orderRepository.create(userId);
    }

    public OrderDto findById(Long id) {
        Order order = orderRepository.findById(id);
        return orderMapper.mapToDto(order);
    }

    public List<OrderDto> findAll() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(order -> orderMapper.mapToDto(order)).toList();
    }

    public void delete(Long id) {
        orderRepository.delete(id);
    }
}
