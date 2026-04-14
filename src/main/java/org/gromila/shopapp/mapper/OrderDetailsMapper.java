package org.gromila.shopapp.mapper;

import org.gromila.shopapp.dto.OrderDetailsCreateDto;
import org.gromila.shopapp.dto.OrderDetailsDto;
import org.gromila.shopapp.entity.OrderDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(uses = ItemMapper.class, unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface OrderDetailsMapper {

    @Mapping(source = "item.id", target = "itemId")
    OrderDetailsDto toDto(OrderDetails orderDetails);

    @Mapping(target = "item", source = "itemId")
    OrderDetails toEntity(OrderDetailsCreateDto dto);
}
