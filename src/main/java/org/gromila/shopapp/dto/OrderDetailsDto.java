package org.gromila.shopapp.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailsDto {
    private Long id;
    private Long orderId;
    private Long itemId;
    private Integer quantity;
}
