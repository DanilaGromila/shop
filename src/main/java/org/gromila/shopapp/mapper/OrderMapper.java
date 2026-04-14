package org.gromila.shopapp.mapper;

import org.gromila.shopapp.dto.OrderDto;
import org.gromila.shopapp.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(uses = {OrderDetailsMapper.class, PaymentMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface OrderMapper {

    @Mapping(source = "user.id", target = "userId")
    OrderDto toDto(Order order);
}
