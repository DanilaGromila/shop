package org.gromila.shopapp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gromila.shopapp.dto.OrderDetailsCreateDto;
import org.gromila.shopapp.dto.OrderDetailsDto;
import org.gromila.shopapp.service.OrderDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/orders/{orderId}/details")
@RequiredArgsConstructor
public class OrderDetailsController {
    private final OrderDetailsService orderDetailsService;

    @PostMapping
    public Long create(@Valid @PathVariable Long userId,
                       @PathVariable Long orderId,
                       @RequestBody OrderDetailsCreateDto orderDetailsCreateDto) {
        return orderDetailsService.create(userId, orderId, orderDetailsCreateDto);
    }

    @GetMapping("/{id}")
    public OrderDetailsDto findById(@PathVariable Long userId,
                                    @PathVariable Long orderId,
                                    @PathVariable Long id) {
        return orderDetailsService.findById(userId, orderId, id);
    }

    @GetMapping
    public List<OrderDetailsDto> findAll(@PathVariable Long userId,
                                         @PathVariable Long orderId) {
        return orderDetailsService.findAll(userId, orderId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long userId,
                       @PathVariable Long orderId,
                       @PathVariable Long id) {
        orderDetailsService.delete(userId, orderId, id);
    }
}
