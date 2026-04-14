package org.gromila.shopapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private Long userId;
    private List<OrderDetailsDto> orderDetails = new ArrayList<>();
    private List<PaymentDto> payments = new ArrayList<>();
}