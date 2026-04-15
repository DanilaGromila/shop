package org.gromila.shopapp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemCreateUpdateDto {

    @NotBlank(message = "Имя не может быть пустым")
    private String name;
}