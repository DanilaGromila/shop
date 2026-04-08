package org.gromila.shopapp.controller;

import lombok.RequiredArgsConstructor;
import org.gromila.shopapp.dto.OrderDto;
import org.gromila.shopapp.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public Long create(@RequestParam Long userId) {
        return orderService.create(userId);
    }

    @GetMapping("/{id}")
    public OrderDto findById(@PathVariable Long userId, @PathVariable Long id) {
        return orderService.findById(userId, id);
    }

    @GetMapping
    public List<OrderDto> findAll(@PathVariable Long userId) {
        return orderService.findAll(userId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long userId, @PathVariable Long id) {
        orderService.delete(userId, id);
    }
}
