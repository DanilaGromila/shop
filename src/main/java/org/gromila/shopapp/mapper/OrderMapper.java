package org.gromila.shopapp.mapper;

import org.gromila.shopapp.dto.OrderDetailsDto;
import org.gromila.shopapp.dto.OrderDto;
import org.gromila.shopapp.dto.PaymentDto;
import org.gromila.shopapp.entity.Order;

import java.util.List;

public class OrderMapper {

    private final OrderDetailsMapper orderDetailsMapper;
    private final PaymentMapper paymentMapper;

    public OrderMapper(OrderDetailsMapper orderDetailsMapper, PaymentMapper paymentMapper) {
        this.orderDetailsMapper = orderDetailsMapper;
        this.paymentMapper = paymentMapper;
    }

    public OrderDto mapToDto(Order order) {
        if (order == null) return null;

        OrderDto dto = new OrderDto();
        dto.setId(order.getId());
        dto.setUser(order.getUser());
        List<OrderDetailsDto> orderDetails = order.getOrderDetails().stream()
                .map(od -> orderDetailsMapper.mapToDto(od))
                .toList();
        dto.setOrderDetails(orderDetails);

        List<PaymentDto> payments = order.getPayments().stream()
                .map(p -> paymentMapper.mapToDto(p))
                .toList();
        dto.setPayments(payments);

        return dto;
    }
}
