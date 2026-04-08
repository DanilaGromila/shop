package org.gromila.shopapp.service;

import lombok.RequiredArgsConstructor;
import org.gromila.shopapp.dto.OrderDetailsCreateDto;
import org.gromila.shopapp.dto.OrderDetailsDto;
import org.gromila.shopapp.entity.Order;
import org.gromila.shopapp.entity.OrderDetails;
import org.gromila.shopapp.mapper.OrderDetailsMapper;
import org.gromila.shopapp.repository.OrderDetailsRepository;
import org.gromila.shopapp.repository.OrderRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailsService {
    private final OrderDetailsRepository orderDetailsRepository;
    private final OrderDetailsMapper orderDetailsMapper;
    private final OrderRepository orderRepository;
    private final OrderService orderService;

    public Long create(Long userId, Long orderId, OrderDetailsCreateDto createdOrderDetails) {
        orderService.findById(userId, orderId);

        OrderDetails orderDetails = orderDetailsMapper.toEntity(createdOrderDetails);
        orderDetails.setOrder(new Order(orderId));
        return orderDetailsRepository.create(orderDetails);
    }

    public OrderDetailsDto findById(Long userId, Long orderId, Long id) {
        OrderDetails orderDetails = orderDetailsRepository.findById(userId, orderId, id);
        return orderDetailsMapper.toDto(orderDetails);
    }

    public List<OrderDetailsDto> findAll(Long userId, Long orderId) {
        List<OrderDetails> orderDetails = orderDetailsRepository.findAll(userId, orderId);
        return orderDetails.stream().map(orderDetailsMapper::toDto).toList();
    }

    public void delete(Long userId, Long orderId, Long id) {
        orderDetailsRepository.delete(userId, orderId, id);
    }
}
