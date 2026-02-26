package org.gromila.shopapp.mapper;

import org.gromila.shopapp.dto.OrderDetailsCreateDto;
import org.gromila.shopapp.dto.OrderDetailsDto;
import org.gromila.shopapp.entity.Item;
import org.gromila.shopapp.entity.Order;
import org.gromila.shopapp.entity.OrderDetails;

public class OrderDetailsMapper {

    public OrderDetailsDto mapToDto(OrderDetails orderDetails) {
        if (orderDetails == null) return null;

        OrderDetailsDto dto = new OrderDetailsDto();
        dto.setId(orderDetails.getId());
        dto.setOrderId(orderDetails.getOrder().getId());
        dto.setItemId(orderDetails.getItem().getId());
        dto.setQuantity(orderDetails.getQuantity());

        return dto;
    }

    public OrderDetails mapToEntity(Long orderId, OrderDetailsCreateDto orderDetailsDto) {
        if (orderDetailsDto == null) return null;

        OrderDetails entity = new OrderDetails();

        Order order = new Order();
        order.setId(orderId);
        entity.setOrder(order);

        Item item = new Item();
        item.setId(orderDetailsDto.getItemId());
        entity.setItem(item);

        entity.setQuantity(entity.getQuantity());

        return entity;
    }
}
