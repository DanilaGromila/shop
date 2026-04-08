package org.gromila.shopapp.service;

import lombok.RequiredArgsConstructor;
import org.gromila.shopapp.dto.OrderDto;
import org.gromila.shopapp.dto.PaymentCreateDto;
import org.gromila.shopapp.dto.PaymentDto;
import org.gromila.shopapp.entity.Order;
import org.gromila.shopapp.entity.Payment;
import org.gromila.shopapp.mapper.PaymentMapper;
import org.gromila.shopapp.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final OrderService orderService;

    public Long create(Long userId, Long orderId, PaymentCreateDto createdPayment) {
        orderService.findById(userId, orderId);

        Payment payment = paymentMapper.toEntity(createdPayment);
        payment.setOrder(new Order(orderId));
        return paymentRepository.create(payment);
    }

    public PaymentDto findById(Long userId, Long orderId, Long id) {
        Payment payment = paymentRepository.findById(userId, orderId, id);
        return paymentMapper.toDto(payment);
    }

    public List<PaymentDto> findAll(Long userId, Long orderId) {
        List<Payment> payments = paymentRepository.findAll(userId, orderId);
        return payments.stream().map(paymentMapper::toDto).toList();
    }

    public void update(Long id, String status) {
        paymentRepository.update(id, status);
    }

    public void delete(Long userId, Long orderId, Long id) {
        paymentRepository.delete(userId, orderId, id);
    }
}