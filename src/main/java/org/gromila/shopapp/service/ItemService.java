package org.gromila.shopapp.service;

import lombok.RequiredArgsConstructor;
import org.gromila.shopapp.dto.ItemCreateDto;
import org.gromila.shopapp.dto.ItemDto;
import org.gromila.shopapp.entity.Item;
import org.gromila.shopapp.mapper.ItemMapper;
import org.gromila.shopapp.repository.ItemRepository;
import org.mapstruct.factory.Mappers;

import java.util.List;

@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper = Mappers.getMapper(ItemMapper.class);

    public Long create(ItemCreateDto createdItem) {
        Item item = itemMapper.toEntity(createdItem);
        return itemRepository.create(item);
    }

    public ItemDto findById(Long id) {
        Item item = itemRepository.findById(id);
        return itemMapper.toDto(item);
    }

    public List<ItemDto> findAll() {
        List<Item> items = itemRepository.findAll();
        return items.stream().map(itemMapper::toDto).toList();
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
