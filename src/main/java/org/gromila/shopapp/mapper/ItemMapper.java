package org.gromila.shopapp.mapper;

import org.gromila.shopapp.dto.ItemCreateDto;
import org.gromila.shopapp.dto.ItemDto;
import org.gromila.shopapp.entity.Item;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(uses = FeedbackMapper.class, unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE,
        componentModel = "spring")
public interface ItemMapper {

    ItemDto toDto(Item item);

    Item toEntity(ItemCreateDto dto);

    default Item mapItem(Long itemId) {
        if (itemId == null) return null;
        Item item = new Item();
        item.setId(itemId);
        return item;
    }
}
