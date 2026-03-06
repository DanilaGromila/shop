package org.gromila.shopapp.mapper;

import org.gromila.shopapp.dto.FeedbackDto;
import org.gromila.shopapp.dto.ItemCreateDto;
import org.gromila.shopapp.dto.ItemDto;
import org.gromila.shopapp.entity.Item;

import java.util.List;

public class ItemMapper {

    private final FeedbackMapper feedbackMapper;

    public ItemMapper(FeedbackMapper feedbackMapper) {
        this.feedbackMapper = feedbackMapper;
    }

    public ItemDto mapToDto(Item item) {
        if (item == null) return null;

        ItemDto dto = new ItemDto();
        dto.setId(item.getId());
        dto.setName(item.getName());
        dto.setRating(item.getRating());
        List<FeedbackDto> feedbacks = item.getFeedbacks().stream()
                .map(f -> feedbackMapper.mapToDto(f))
                .toList();
        dto.setFeedbacks(feedbacks);

        return dto;
    }

    public Item mapToEntity(ItemCreateDto itemDto) {
        if (itemDto == null) return null;

        Item entity = new Item();
        entity.setName(itemDto.getName());
        entity.setRating(itemDto.getRating());

        return entity;
    }
}
