package org.gromila.shopapp.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {
    private Long id;
    private String name;
    private Double rating;
    private List<FeedbackDto> feedbacks;
}