package org.gromila.shopapp.service;

import lombok.RequiredArgsConstructor;
import org.gromila.shopapp.dto.FeedbackCreateDto;
import org.gromila.shopapp.dto.FeedbackDto;
import org.gromila.shopapp.entity.Feedback;
import org.gromila.shopapp.entity.Item;
import org.gromila.shopapp.mapper.FeedbackMapper;
import org.gromila.shopapp.repository.FeedbackRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final ItemService itemService;
    private final FeedbackMapper feedbackMapper;

    @Transactional
    public Long create(Long itemId, FeedbackCreateDto createdFeedback) {
        itemService.findById(itemId);
        Feedback feedback = feedbackMapper.toEntity(createdFeedback);
        feedback.setItem(new Item(itemId));
        Feedback savedFeedback = feedbackRepository.save(feedback);
        itemService.updateItemRating(itemId);
        return savedFeedback.getId();
    }

    public FeedbackDto findById(Long itemId, Long id) {
        Feedback feedback = feedbackRepository.findByIdAndItemId(id, itemId).
                orElseThrow(() -> new RuntimeException("Feedback no found"));
        return feedbackMapper.toDto(feedback);
    }

    public List<FeedbackDto> findAll(Long itemId) {
        List<Feedback> feedbacks = feedbackRepository.findAllByItemId(itemId);
        return feedbacks.stream().map(feedbackMapper::toDto).toList();
    }

    @Transactional
    public void delete(Long itemId, Long id) {
        Feedback feedback = feedbackRepository.findByIdAndItemId(id, itemId).
                orElseThrow(() -> new RuntimeException("Feedback no found"));
        feedbackRepository.delete(feedback);
    }
}
