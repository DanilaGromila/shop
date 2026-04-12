package org.gromila.shopapp.service;

import lombok.RequiredArgsConstructor;
import org.gromila.shopapp.dto.PaymentCreateDto;
import org.gromila.shopapp.dto.PaymentDto;
import org.gromila.shopapp.entity.Order;
import org.gromila.shopapp.entity.Payment;
import org.gromila.shopapp.mapper.PaymentMapper;
import org.gromila.shopapp.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final OrderService orderService;

    @Transactional
    public Long create(Long userId, Long orderId, PaymentCreateDto createdPayment) {
        orderService.findById(userId, orderId);
        Payment payment = paymentMapper.toEntity(createdPayment);
        payment.setOrder(new Order(orderId));
        return paymentRepository.save(payment).getId();
    }

    public PaymentDto findById(Long userId, Long orderId, Long id) {
        Payment payment = paymentRepository.findByIdAndOrderIdAndOrderUserId(id, orderId, userId)
                .orElseThrow(() -> new RuntimeException("Payment is not found"));
        return paymentMapper.toDto(payment);
    }

    public List<PaymentDto> findAll(Long userId, Long orderId) {
        List<Payment> payments = paymentRepository
                .findAllByOrderIdAndOrderUserId(orderId, userId);
        return payments.stream().map(paymentMapper::toDto).toList();
    }

    @Transactional
    public void update(Long id, Long userId, Long orderId, String status) {
        Payment payment = paymentRepository.findByIdAndOrderIdAndOrderUserId(id, orderId, userId)
                .orElseThrow(() -> new RuntimeException("Payment is not found"));
        payment.setPaymentStatus(status);
        paymentRepository.save(payment);
    }

    @Transactional
    public void delete(Long userId, Long orderId, Long id) {
        Payment payment = paymentRepository.findByIdAndOrderIdAndOrderUserId(id, orderId, userId)
                .orElseThrow(() -> new RuntimeException("Payment is not found"));
        paymentRepository.delete(payment);
    }
}