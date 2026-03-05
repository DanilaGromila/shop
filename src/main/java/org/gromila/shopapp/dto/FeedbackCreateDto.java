package org.gromila.shopapp.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackCreateDto {
    private String text;
    private Integer stars;
}
