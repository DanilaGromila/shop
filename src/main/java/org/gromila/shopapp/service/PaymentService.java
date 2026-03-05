package org.gromila.shopapp.service;

import org.gromila.shopapp.dto.PaymentCreateDto;
import org.gromila.shopapp.dto.PaymentDto;
import org.gromila.shopapp.entity.Payment;
import org.gromila.shopapp.mapper.PaymentMapper;
import org.gromila.shopapp.repository.PaymentRepository;

import java.util.List;

public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    public PaymentService(PaymentRepository paymentRepository, PaymentMapper paymentMapper) {
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
    }

    public Long create(Long orderId, PaymentCreateDto createdPayment) {
        Payment payment = paymentMapper.mapToEntity(orderId, createdPayment);
        return paymentRepository.create(payment);
    }

    public PaymentDto findById(Long id) {
        Payment payment = paymentRepository.findById(id);
        return paymentMapper.mapToDto(payment);
    }

    public List<PaymentDto> findAll() {
        List<Payment> payments = paymentRepository.findAll();
        return payments.stream().map(payment -> paymentMapper.mapToDto(payment)).toList();
    }

    public void update(Long id, String status) {
        paymentRepository.update(id, status);
    }

    public void delete(Long id) {
        paymentRepository.delete(id);
    }
}