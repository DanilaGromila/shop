package org.gromila.shopapp.mapper;

import org.gromila.shopapp.dto.FeedbackCreateDto;
import org.gromila.shopapp.dto.FeedbackDto;
import org.gromila.shopapp.entity.Feedback;
import org.gromila.shopapp.entity.Item;

public class FeedbackMapper {

    public FeedbackDto mapToDto(Feedback feedback) {
        if (feedback == null) return null;

        FeedbackDto dto = new FeedbackDto();
        dto.setId(feedback.getId());
        dto.setText(feedback.getText());
        dto.setItemId(feedback.getItem().getId());
        dto.setStars(feedback.getStars());

        return dto;
    }

    public Feedback mapToEntity(Long itemId, FeedbackCreateDto feedbackDto) {
        if (feedbackDto == null) return null;

        Feedback entity = new Feedback();
        entity.setText(feedbackDto.getText());
        entity.setStars(feedbackDto.getStars());

        Item item = new Item();
        item.setId(itemId);
        entity.setItem(item);

        return entity;
    }
}
