package org.gromila.shopapp.mapper;

import org.gromila.shopapp.dto.RoleDto;
import org.gromila.shopapp.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(uses = AuthorityMapper.class, unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface RoleMapper {

    @Mapping(source = "authorities", target = "authorities")
    RoleDto toDto(Role role);

    default String mapRolesToNames(Role role) {
        if (role == null) return null;
        return role.getName();
    }
}

