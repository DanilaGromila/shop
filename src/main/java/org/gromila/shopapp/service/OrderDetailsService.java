package org.gromila.shopapp.service;

import lombok.RequiredArgsConstructor;
import org.gromila.shopapp.dto.OrderDetailsCreateDto;
import org.gromila.shopapp.dto.OrderDetailsDto;
import org.gromila.shopapp.entity.Order;
import org.gromila.shopapp.entity.OrderDetails;
import org.gromila.shopapp.exception.ApplicationException;
import org.gromila.shopapp.mapper.OrderDetailsMapper;
import org.gromila.shopapp.repository.OrderDetailsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailsService {
    private final OrderDetailsRepository orderDetailsRepository;
    private final OrderDetailsMapper orderDetailsMapper;
    private final OrderService orderService;

    @Transactional
    public Long create(Long userId, Long orderId, OrderDetailsCreateDto createdOrderDetails) {
        orderService.findById(orderId, userId);
        OrderDetails orderDetails = orderDetailsMapper.toEntity(createdOrderDetails);
        orderDetails.setOrder(new Order(orderId));
        return orderDetailsRepository.save(orderDetails).getId();
    }

    @Transactional(readOnly = true)
    public OrderDetailsDto findById(Long userId, Long orderId, Long id) {
        OrderDetails orderDetails = orderDetailsRepository.findByIdAndOrderIdAndOrderUserId(id, orderId, userId)
                .orElseThrow(() -> new ApplicationException("Details not found", HttpStatus.NOT_FOUND));
        return orderDetailsMapper.toDto(orderDetails);
    }

    @Transactional(readOnly = true)
    public List<OrderDetailsDto> findAll(Long userId, Long orderId) {
        List<OrderDetails> orderDetails = orderDetailsRepository.findAllByOrderIdAndOrderUserId(orderId, userId);
        return orderDetails.stream().map(orderDetailsMapper::toDto).toList();
    }

    @Transactional
    public void delete(Long userId, Long orderId, Long id) {
        OrderDetails orderDetails = orderDetailsRepository.findByIdAndOrderIdAndOrderUserId(id, orderId, userId)
                .orElseThrow(() -> new ApplicationException("Details not found", HttpStatus.NOT_FOUND));
        orderDetailsRepository.delete(orderDetails);
    }
}
