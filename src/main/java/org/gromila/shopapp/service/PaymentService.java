package org.gromila.shopapp.service;

import lombok.RequiredArgsConstructor;
import org.gromila.shopapp.dto.PaymentCreateDto;
import org.gromila.shopapp.dto.PaymentDto;
import org.gromila.shopapp.entity.Order;
import org.gromila.shopapp.entity.Payment;
import org.gromila.shopapp.mapper.PaymentMapper;
import org.gromila.shopapp.repository.PaymentRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper = Mappers.getMapper(PaymentMapper.class);

    public Long create(Long orderId, PaymentCreateDto createdPayment) {
        Payment payment = paymentMapper.toEntity(createdPayment);
        payment.setOrder(new Order(orderId));
        return paymentRepository.create(payment);
    }

    public PaymentDto findById(Long id) {
        Payment payment = paymentRepository.findById(id);
        return paymentMapper.toDto(payment);
    }

    public List<PaymentDto> findAll() {
        List<Payment> payments = paymentRepository.findAll();
        return payments.stream().map(paymentMapper::toDto).toList();
    }

    public void update(Long id, String status) {
        paymentRepository.update(id, status);
    }

    public void delete(Long id) {
        paymentRepository.delete(id);
    }
}