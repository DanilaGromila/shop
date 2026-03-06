package org.gromila.shopapp.service;

import lombok.RequiredArgsConstructor;
import org.gromila.shopapp.dto.OrderDetailsCreateDto;
import org.gromila.shopapp.dto.OrderDetailsDto;
import org.gromila.shopapp.entity.OrderDetails;
import org.gromila.shopapp.mapper.ItemMapper;
import org.gromila.shopapp.mapper.OrderDetailsMapper;
import org.gromila.shopapp.repository.OrderDetailsRepository;
import org.mapstruct.factory.Mappers;

import java.util.List;

@RequiredArgsConstructor
public class OrderDetailsService {
    private final OrderDetailsRepository orderDetailsRepository;
    private final OrderDetailsMapper orderDetailsMapper = Mappers.getMapper(OrderDetailsMapper.class);

    public Long create(Long orderId, OrderDetailsCreateDto createdOrderDetails) {
        OrderDetails orderDetails = orderDetailsMapper.toEntity(orderId, createdOrderDetails);
        return orderDetailsRepository.create(orderDetails);
    }

    public OrderDetailsDto findById(Long id) {
        OrderDetails orderDetails = orderDetailsRepository.findById(id);
        return orderDetailsMapper.toDto(orderDetails);
    }

    public List<OrderDetailsDto> findAll() {
        List<OrderDetails> orderDetails = orderDetailsRepository.findAll();
        return orderDetails.stream().map(orderDetailsMapper::toDto).toList();
    }

    public void delete(Long id) {
        orderDetailsRepository.delete(id);
    }
}
