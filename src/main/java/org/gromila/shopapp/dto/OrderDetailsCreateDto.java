package org.gromila.shopapp.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailsCreateDto {

    @NotNull(message = "укажите товар")
    private Long itemId;

    @NotNull(message = "укажите количество")
    private Integer quantity;
}
