package org.gromila.shopapp.service;

import lombok.RequiredArgsConstructor;
import org.gromila.shopapp.dto.FeedbackCreateDto;
import org.gromila.shopapp.dto.FeedbackDto;
import org.gromila.shopapp.entity.Feedback;
import org.gromila.shopapp.mapper.AuthorityMapper;
import org.gromila.shopapp.mapper.FeedbackMapper;
import org.gromila.shopapp.repository.FeedbackRepository;
import org.mapstruct.factory.Mappers;

import java.util.List;

@RequiredArgsConstructor
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final ItemService itemService;
    private final FeedbackMapper feedbackMapper = Mappers.getMapper(FeedbackMapper.class);

    public Long create(Long itemId, FeedbackCreateDto createdFeedback) {
        Feedback feedback = feedbackMapper.toEntity(itemId, createdFeedback);
        Long feedbackId = feedbackRepository.create(feedback);
        itemService.updateItemRating(itemId);
        return feedbackId;
    }

    public FeedbackDto findById(Long id) {
        Feedback feedback = feedbackRepository.findById(id);
        return feedbackMapper.toDto(feedback);
    }

    public List<FeedbackDto> findAll() {
        List<Feedback> feedbacks = feedbackRepository.findAll();
        return feedbacks.stream().map(feedbackMapper::toDto).toList();
    }

    public void delete(Long id) {
        feedbackRepository.delete(id);
    }
}
