package org.gromila.shopapp.service;

import lombok.RequiredArgsConstructor;
import org.gromila.shopapp.dto.PaymentDto;
import org.gromila.shopapp.entity.Order;
import org.gromila.shopapp.entity.Payment;
import org.gromila.shopapp.entity.PaymentStatus;
import org.gromila.shopapp.exception.ApplicationException;
import org.gromila.shopapp.mapper.PaymentMapper;
import org.gromila.shopapp.repository.PaymentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final OrderService orderService;

    @Transactional
    public Long create(Long userId, Long orderId) {
        orderService.findById(userId, orderId);
        Payment payment = new Payment();
        payment.setOrder(new Order(orderId));
        payment.setPaymentStatus(PaymentStatus.IN_PROCESS);
        return paymentRepository.save(payment).getId();
    }

    @Transactional(readOnly = true)
    public PaymentDto findById(Long userId, Long orderId, Long id) {
        Payment payment = paymentRepository.findByIdAndOrderIdAndOrderUserId(id, orderId, userId)
                .orElseThrow(() -> new ApplicationException("Payment is not found", HttpStatus.NOT_FOUND));
        return paymentMapper.toDto(payment);
    }

    @Transactional(readOnly = true)
    public List<PaymentDto> findAll(Long userId, Long orderId) {
        List<Payment> payments = paymentRepository
                .findAllByOrderIdAndOrderUserId(orderId, userId);
        return payments.stream().map(paymentMapper::toDto).toList();
    }

    @Transactional
    public void update(Long id, Long userId, Long orderId, PaymentStatus status) {
        Payment payment = paymentRepository.findByIdAndOrderIdAndOrderUserId(id, orderId, userId)
                .orElseThrow(() -> new ApplicationException("Payment is not found", HttpStatus.NOT_FOUND));
        payment.setPaymentStatus(status);
        paymentRepository.save(payment);
    }

    @Transactional
    public PaymentStatus pay(Long userId, Long orderId, Long id) {
        Payment payment = paymentRepository.findByIdAndOrderIdAndOrderUserId(id, orderId, userId)
                .orElseThrow(() -> new ApplicationException("Payment is not found", HttpStatus.NOT_FOUND));
        if (payment.getPaymentStatus() == PaymentStatus.SUCCESSFUL) {
            throw new ApplicationException("Order is already paid", HttpStatus.CONFLICT);
        }
        payment.setPaymentStatus(getRandomPaymentStatus());
        paymentRepository.save(payment);
        return payment.getPaymentStatus();
    }

    private PaymentStatus getRandomPaymentStatus() {
        return new Random().nextBoolean() ? PaymentStatus.SUCCESSFUL : PaymentStatus.REJECTED;
    }

    @Transactional
    public void timeOutInProcessPayments(Long minutes) {
        LocalDateTime date = LocalDateTime.now().minusMinutes(minutes);
        paymentRepository.updateExpiredPayments(date);
    }

    @Transactional
    public void delete(Long userId, Long orderId, Long id) {
        Payment payment = paymentRepository.findByIdAndOrderIdAndOrderUserId(id, orderId, userId)
                .orElseThrow(() -> new ApplicationException("Payment is not found", HttpStatus.NOT_FOUND));
        paymentRepository.delete(payment);
    }
}