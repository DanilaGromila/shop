package org.gromila.shopapp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gromila.shopapp.dto.ItemCreateUpdateDto;
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
    public Long create(@Valid @RequestBody ItemCreateUpdateDto itemCreateDto) {
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

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @Valid @RequestBody ItemCreateUpdateDto itemUpdateDto) {
        itemService.update(id, itemUpdateDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        itemService.delete(id);
    }
}
