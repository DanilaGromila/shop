package org.gromila.shopapp.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackCreateDto {
    private String text;

    @NotNull(message = "Поставьте оценку")
    private Integer stars;
}
