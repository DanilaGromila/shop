package org.gromila.shopapp.service;

import lombok.RequiredArgsConstructor;
import org.gromila.shopapp.dto.ItemCreateDto;
import org.gromila.shopapp.dto.ItemDto;
import org.gromila.shopapp.entity.Item;
import org.gromila.shopapp.mapper.ItemMapper;
import org.gromila.shopapp.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    public Long create(ItemCreateDto createdItem) {
        Item item = itemMapper.toEntity(createdItem);
        return itemRepository.save(item).getId();
    }

    public ItemDto findById(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item no found"));
        return itemMapper.toDto(item);
    }

    public List<ItemDto> findAll() {
        List<Item> items = itemRepository.findAll();
        return items.stream().map(itemMapper::toDto).toList();
    }

    @Transactional
    public void update(Long id, String name) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item no found"));
        item.setName(name);
        itemRepository.save(item);
    }

    @Transactional
    public void updateItemRating(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item no found"));

        double averageRating = item.getFeedbacks().stream()
                .map(feedback -> feedback.getStars())
                .mapToInt(value -> value)
                .average()
                .orElse(0.0);

        item.setRating(averageRating);
        itemRepository.save(item);
    }

    @Transactional
    public void delete(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item no found"));
        itemRepository.delete(item);
    }
}
