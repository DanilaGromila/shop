package org.gromila.shopapp.service;

import lombok.RequiredArgsConstructor;
import org.gromila.shopapp.dto.OrderDto;
import org.gromila.shopapp.entity.Order;
import org.gromila.shopapp.mapper.OrderDetailsMapper;
import org.gromila.shopapp.mapper.OrderMapper;
import org.gromila.shopapp.repository.OrderRepository;
import org.mapstruct.factory.Mappers;

import java.util.List;

@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper = Mappers.getMapper(OrderMapper.class);

    public Long create(Long userId) {
        return orderRepository.create(userId);
    }

    public OrderDto findById(Long id) {
        Order order = orderRepository.findById(id);
        return orderMapper.toDto(order);
    }

    public List<OrderDto> findAll() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(orderMapper::toDto).toList();
    }

    public void delete(Long id) {
        orderRepository.delete(id);
    }
}
