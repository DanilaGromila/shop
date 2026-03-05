package org.gromila.shopapp.service;

import org.gromila.shopapp.dto.FeedbackCreateDto;
import org.gromila.shopapp.dto.FeedbackDto;
import org.gromila.shopapp.entity.Feedback;
import org.gromila.shopapp.mapper.FeedbackMapper;
import org.gromila.shopapp.repository.FeedbackRepository;

import java.util.List;

public class FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final FeedbackMapper feedbackMapper;
    private final ItemService itemService;

    public FeedbackService(FeedbackRepository feedbackRepository, FeedbackMapper feedbackMapper, ItemService itemService) {
        this.feedbackRepository = feedbackRepository;
        this.feedbackMapper = feedbackMapper;
        this.itemService = itemService;
    }

    public Long create(Long itemId, FeedbackCreateDto createdFeedback) {
        Feedback feedback = feedbackMapper.mapToEntity(itemId, createdFeedback);
        Long feedbackId = feedbackRepository.create(feedback);
        itemService.updateItemRating(itemId);
        return feedbackId;
    }

    public FeedbackDto findById(Long id) {
        Feedback feedback = feedbackRepository.findById(id);
        return feedbackMapper.mapToDto(feedback);
    }

    public List<FeedbackDto> findAll() {
        List<Feedback> feedbacks = feedbackRepository.findAll();
        return feedbacks.stream().map(feedback -> feedbackMapper.mapToDto(feedback)).toList();
    }

    public void delete(Long id) {
        feedbackRepository.delete(id);
    }
}
