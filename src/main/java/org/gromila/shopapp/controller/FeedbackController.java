package org.gromila.shopapp.controller;

import lombok.RequiredArgsConstructor;
import org.gromila.shopapp.dto.FeedbackCreateDto;
import org.gromila.shopapp.dto.FeedbackDto;
import org.gromila.shopapp.service.FeedbackService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items/{itemId}/feedbacks")
@RequiredArgsConstructor
public class FeedbackController {
    private final FeedbackService feedbackService;

    @PostMapping
    public Long create(@PathVariable Long itemId,
                       @RequestBody FeedbackCreateDto feedbackCreateDto){
        return feedbackService.create(itemId, feedbackCreateDto);
    }

    @GetMapping
    public List<FeedbackDto> findAll(@PathVariable Long itemId) {
        return feedbackService.findAll(itemId);
    }

    @GetMapping("/{id}")
    public FeedbackDto findById(@PathVariable Long itemId, @PathVariable Long id){
        return feedbackService.findById(itemId,id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long itemId, @PathVariable Long id) {
        feedbackService.delete(itemId, id);
    }
}
