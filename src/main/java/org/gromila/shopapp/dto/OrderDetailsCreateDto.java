package org.gromila.shopapp.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailsCreateDto {
    private Long itemId;
    private Integer quantity;
}
