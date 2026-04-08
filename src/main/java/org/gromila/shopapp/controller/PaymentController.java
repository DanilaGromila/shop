package org.gromila.shopapp.controller;

import lombok.RequiredArgsConstructor;
import org.gromila.shopapp.dto.PaymentCreateDto;
import org.gromila.shopapp.dto.PaymentDto;
import org.gromila.shopapp.service.PaymentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/orders/{orderId}/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    public Long create(@PathVariable Long userId,
                       @PathVariable Long orderId,
                       @RequestBody PaymentCreateDto paymentCreateDto) {
        return paymentService.create(userId, orderId, paymentCreateDto);
    }

    @GetMapping("/{id}")
    public PaymentDto findById(@PathVariable Long userId,
                               @PathVariable Long orderId,
                               @PathVariable Long id) {
        return paymentService.findById(userId, orderId, id);
    }

    @GetMapping
    public List<PaymentDto> findAll(@PathVariable Long userId,
                                    @PathVariable Long orderId) {
        return paymentService.findAll(userId, orderId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long userId,
                       @PathVariable Long orderId,
                       @PathVariable  Long id) {
        paymentService.delete(userId, orderId, id);
    }
}
