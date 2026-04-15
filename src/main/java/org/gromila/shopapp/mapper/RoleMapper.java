package org.gromila.shopapp.mapper;

import org.gromila.shopapp.dto.RoleDto;
import org.gromila.shopapp.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(uses = AuthorityMapper.class, unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface RoleMapper {

    RoleDto toDto(Role role);
}

