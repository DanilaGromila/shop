package org.gromila.shopapp.mapper;

import org.gromila.shopapp.dto.RoleDto;
import org.gromila.shopapp.entity.Role;

import java.util.List;

public class RoleMapper {
    public RoleDto mapToDto(Role role) {
        if (role == null) return null;

        RoleDto dto = new RoleDto();
        dto.setId(role.getId());
        dto.setName(role.getName());
        List<String> authorities = role.getAuthorities().stream()
                .map(a -> a.getName())
                .toList();
        dto.setAuthorities(authorities);

        return dto;
    }
}

