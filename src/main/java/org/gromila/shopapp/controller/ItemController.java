package org.gromila.shopapp.controller;

import lombok.RequiredArgsConstructor;
import org.gromila.shopapp.dto.ItemCreateDto;
import org.gromila.shopapp.dto.ItemDto;
import org.gromila.shopapp.service.ItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @PostMapping
    public Long create(@RequestBody ItemCreateDto itemCreateDto) {
        return itemService.create(itemCreateDto);
    }

    @GetMapping("/{id}")
    public ItemDto findById(@PathVariable Long id) {
        return itemService.findById(id);
    }

    @GetMapping
    public List<ItemDto> findAll() {
        return itemService.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        itemService.delete(id);
    }
}
