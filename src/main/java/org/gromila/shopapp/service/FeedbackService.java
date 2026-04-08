package org.gromila.shopapp.service;

import lombok.RequiredArgsConstructor;
import org.gromila.shopapp.dto.FeedbackCreateDto;
import org.gromila.shopapp.dto.FeedbackDto;
import org.gromila.shopapp.entity.Feedback;
import org.gromila.shopapp.entity.Item;
import org.gromila.shopapp.mapper.FeedbackMapper;
import org.gromila.shopapp.repository.FeedbackRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final ItemService itemService;
    private final FeedbackMapper feedbackMapper;

    public Long create(Long itemId, FeedbackCreateDto createdFeedback) {
        itemService.findById(itemId);
        Feedback feedback = feedbackMapper.toEntity(createdFeedback);
        feedback.setItem(new Item(itemId));
        Long feedbackId = feedbackRepository.create(feedback);
        itemService.updateItemRating(itemId);
        return feedbackId;
    }

    public FeedbackDto findById(Long itemId,Long id) {
        Feedback feedback = feedbackRepository.findById(itemId, id);
        return feedbackMapper.toDto(feedback);
    }

    public List<FeedbackDto> findAll(Long itemId) {
        List<Feedback> feedbacks = feedbackRepository.findAll(itemId);
        return feedbacks.stream().map(feedbackMapper::toDto).toList();
    }

    public void delete(Long itemId, Long id) {
        feedbackRepository.delete(itemId, id);
    }
}
