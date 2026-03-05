package org.gromila.shopapp.service;

import org.gromila.shopapp.dto.OrderDetailsCreateDto;
import org.gromila.shopapp.dto.OrderDetailsDto;
import org.gromila.shopapp.entity.OrderDetails;
import org.gromila.shopapp.mapper.OrderDetailsMapper;
import org.gromila.shopapp.repository.OrderDetailsRepository;

import java.util.List;

public class OrderDetailsService {
    private final OrderDetailsRepository orderDetailsRepository;
    private final OrderDetailsMapper orderDetailsMapper;

    public OrderDetailsService(OrderDetailsRepository orderDetailsRepository, OrderDetailsMapper orderDetailsMapper) {
        this.orderDetailsRepository = orderDetailsRepository;
        this.orderDetailsMapper = orderDetailsMapper;
    }

    public Long create(Long orderId, OrderDetailsCreateDto createdOrderDetails) {
        OrderDetails orderDetails = orderDetailsMapper.mapToEntity(orderId, createdOrderDetails);
        return orderDetailsRepository.create(orderDetails);
    }

    public OrderDetailsDto findById(Long id) {
        OrderDetails orderDetails = orderDetailsRepository.findById(id);
        return orderDetailsMapper.mapToDto(orderDetails);
    }

    public List<OrderDetailsDto> findAll() {
        List<OrderDetails> orderDetails = orderDetailsRepository.findAll();
        return orderDetails.stream().map(orderDetail -> orderDetailsMapper.mapToDto(orderDetail)).toList();
    }

    public void delete(Long id) {
        orderDetailsRepository.delete(id);
    }
}
