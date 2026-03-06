package org.gromila.shopapp.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackDto {
    private Long id;
    private String text;
    private Long itemId;
    private Integer stars;
}

