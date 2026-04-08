package org.gromila.shopapp.mapper;

import org.gromila.shopapp.dto.OrderDto;
import org.gromila.shopapp.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(uses = {OrderDetailsMapper.class, PaymentMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE,
        componentModel = "spring")
public interface OrderMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "orderDetails", target = "orderDetails")
    @Mapping(source = "payments", target = "payments")
    OrderDto toDto(Order order);

    default Order mapOrder(Long orderId) {
        if (orderId == null) return null;
        Order order = new Order();
        order.setId(orderId);
        return order;
    }

    default Long mapOrdersToIds(Order order) {
        if (order == null) return null;
        return order.getId();
    }
}
