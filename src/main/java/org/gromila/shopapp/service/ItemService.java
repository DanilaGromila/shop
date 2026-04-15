package org.gromila.shopapp.service;

import lombok.RequiredArgsConstructor;
import org.gromila.shopapp.dto.ItemCreateUpdateDto;
import org.gromila.shopapp.dto.ItemDto;
import org.gromila.shopapp.entity.Item;
import org.gromila.shopapp.exception.ApplicationException;
import org.gromila.shopapp.mapper.ItemMapper;
import org.gromila.shopapp.repository.ItemRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    @Transactional
    public Long create(ItemCreateUpdateDto createdItem) {
        Item item = itemMapper.toEntity(createdItem);
        return itemRepository.save(item).getId();
    }

    @Transactional(readOnly = true)
    public ItemDto findById(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("Item not found", HttpStatus.NOT_FOUND));
        return itemMapper.toDto(item);
    }

    @Transactional(readOnly = true)
    public List<ItemDto> findAll() {
        List<Item> items = itemRepository.findAll();
        return items.stream().map(itemMapper::toDto).toList();
    }

    @Transactional
    public void update(Long id, ItemCreateUpdateDto itemUpdateDto) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("Item not found", HttpStatus.NOT_FOUND));
        item.setName(itemUpdateDto.getName());
        itemRepository.save(item);
    }

    @Transactional
    public void updateItemRating(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("Item not found", HttpStatus.NOT_FOUND));

        BigDecimal averageRating = BigDecimal.valueOf(item.getFeedbacks().stream()
                .map(feedback -> feedback.getStars())
                .mapToInt(value -> value)
                .average()
                .orElse(0.0)).setScale(2, RoundingMode.HALF_DOWN);

        item.setRating(averageRating);
        itemRepository.save(item);
    }

    @Transactional
    public void delete(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("Item not found", HttpStatus.NOT_FOUND));
        itemRepository.delete(item);
    }
}
