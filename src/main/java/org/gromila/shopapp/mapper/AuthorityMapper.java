package org.gromila.shopapp.mapper;

import org.gromila.shopapp.dto.AuthorityDto;
import org.gromila.shopapp.entity.Authority;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AuthorityMapper {

    AuthorityDto toDto(Authority authority);

    default String mapAuthoritiesToNames(Authority authority) {
        if (authority == null) return null;
        return authority.getName();
    }
}
