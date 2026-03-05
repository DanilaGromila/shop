package org.gromila.shopapp.mapper;

import org.gromila.shopapp.dto.UserCreateDto;
import org.gromila.shopapp.dto.UserDto;
import org.gromila.shopapp.entity.User;

import java.util.List;

public class UserMapper {
    public UserDto mapToDto(User user) {
        if (user == null) return null;

        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setSurname(user.getSurname());
        dto.setLogin(user.getLogin());
        List<Long> orderIds = user.getOrders().stream()
                .map(o -> o.getId())
                .toList();
        dto.setOrderIds(orderIds);

        List<String> roles = user.getRoles().stream()
                .map(r -> r.getName())
                .toList();
        dto.setRoles(roles);

        return dto;
    }

    public User mapToEntity(UserCreateDto user) {
        if (user == null) return null;

        User entity = new User();
        entity.setName(user.getName());
        entity.setSurname(user.getSurname());
        entity.setLogin(user.getLogin());
        entity.setPassword(user.getPassword());

        return entity;
    }
}