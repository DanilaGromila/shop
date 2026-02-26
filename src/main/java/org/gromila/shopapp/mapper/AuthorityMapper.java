package org.gromila.shopapp.mapper;

import org.gromila.shopapp.dto.AuthorityDto;
import org.gromila.shopapp.entity.Authority;

public class AuthorityMapper {

    public AuthorityDto mapToDto(Authority authority) {
        if (authority == null) return null;

        AuthorityDto dto = new AuthorityDto();
        dto.setId(authority.getId());
        dto.setName(authority.getName());

        return dto;
    }
}
