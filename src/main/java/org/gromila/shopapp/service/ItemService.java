package org.gromila.shopapp.service;

import org.gromila.shopapp.dto.ItemCreateDto;
import org.gromila.shopapp.dto.ItemDto;
import org.gromila.shopapp.entity.Item;
import org.gromila.shopapp.mapper.ItemMapper;
import org.gromila.shopapp.repository.ItemRepository;

import java.util.List;

public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    public ItemService(ItemRepository itemRepository, ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }

    public Long create(ItemCreateDto createdItem) {
        Item item = itemMapper.mapToEntity(createdItem);
        return itemRepository.create(item);
    }

    public ItemDto findById(Long id) {
        Item item = itemRepository.findById(id);
        return itemMapper.mapToDto(item);
    }

    public List<ItemDto> findAll() {
        List<Item> items = itemRepository.findAll();
        return items.stream().map(item -> itemMapper.mapToDto(item)).toList();
    }

    public void update(Long id, String name) {
        itemRepository.update(id, name);
    }

    public void updateItemRating(Long itemId) {
        Item item = itemRepository.findById(itemId);

        double averageRating = item.getFeedbacks().stream()
                .map(feedback -> feedback.getStars())
                .mapToInt(value -> value)
                .average()
                .orElse(0.0);

        itemRepository.updateRating(itemId, item, averageRating);
    }

    public void delete(Long id) {
        itemRepository.delete(id);
    }
}
