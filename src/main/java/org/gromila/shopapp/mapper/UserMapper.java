package org.gromila.shopapp.mapper;

import org.gromila.shopapp.dto.UserCreateUpdateDto;
import org.gromila.shopapp.dto.UserDto;
import org.gromila.shopapp.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(uses = {OrderMapper.class, RoleMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);

    User toEntity(UserCreateUpdateDto dto);
}