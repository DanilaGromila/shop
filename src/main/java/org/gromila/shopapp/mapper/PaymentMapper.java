package org.gromila.shopapp.mapper;

import org.gromila.shopapp.dto.PaymentCreateDto;
import org.gromila.shopapp.dto.PaymentDto;
import org.gromila.shopapp.entity.Order;
import org.gromila.shopapp.entity.Payment;

public class PaymentMapper {
    public PaymentDto mapToDto(Payment payment) {
        if (payment == null) return null;

        PaymentDto dto = new PaymentDto();
        dto.setId(payment.getId());
        dto.setPaymentStatus(payment.getPaymentStatus());

        return dto;
    }

    public Payment mapToEntity(Long orderId, PaymentCreateDto paymentDto) {
        if (paymentDto == null) return null;

        Payment entity = new Payment();
        entity.setPaymentStatus(paymentDto.getPaymentStatus());

        Order order = new Order();
        order.setId(orderId);
        entity.setOrder(order);

        return entity;
    }
}
