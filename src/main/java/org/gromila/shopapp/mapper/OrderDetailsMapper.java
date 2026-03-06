package org.gromila.shopapp.mapper;

import org.gromila.shopapp.dto.OrderDetailsCreateDto;
import org.gromila.shopapp.dto.OrderDetailsDto;
import org.gromila.shopapp.entity.OrderDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

@Mapper(uses = {ItemMapper.class, OrderMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface OrderDetailsMapper {

    @Mapping(source = "order.id", target = "orderId")
    @Mapping(source = "item.id", target = "itemId")
    OrderDetailsDto toDto(OrderDetails orderDetails);

    @Mapping(target = "order", source = "orderId")
    @Mapping(target = "item", source = "dto.itemId")
    OrderDetails toEntity(Long orderId, OrderDetailsCreateDto dto);
}
