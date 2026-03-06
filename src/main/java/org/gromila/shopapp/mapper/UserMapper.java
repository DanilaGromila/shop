package org.gromila.shopapp.mapper;

import org.gromila.shopapp.dto.UserCreateDto;
import org.gromila.shopapp.dto.UserDto;
import org.gromila.shopapp.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(uses = {OrderMapper.class, RoleMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    @Mapping(source = "orders", target = "orderIds")
    @Mapping(source = "roles", target = "roles")
    UserDto toDto(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "roles", ignore = true)
    User toEntity(UserCreateDto dto);
}