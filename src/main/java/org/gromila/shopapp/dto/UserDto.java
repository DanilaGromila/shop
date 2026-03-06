package org.gromila.shopapp.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String surname;
    private String login;
    private List<Long> orderIds;
    private List<String> roles;
}