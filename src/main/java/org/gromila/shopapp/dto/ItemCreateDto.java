package org.gromila.shopapp.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemCreateDto {
    private String name;
    private Double rating;
}