package org.gromila.shopapp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gromila.shopapp.dto.UserCreateUpdateDto;
import org.gromila.shopapp.dto.UserDto;
import org.gromila.shopapp.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public Long create(@Valid @RequestBody UserCreateUpdateDto userCreateDto) {
        return userService.create(userCreateDto);
    }

    @GetMapping("/{id}")
    public UserDto findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping
    public List<UserDto> findAll() {//работает
        return userService.findAll();
    }

    @PutMapping("/{id}")
    public UserDto update(@PathVariable Long id, @Valid @RequestBody UserCreateUpdateDto userCreateDto) {
        return userService.update(id, userCreateDto);
    }

    @PostMapping("/{userId}/roles/{roleId}")
    public void addRole(@PathVariable Long userId, @PathVariable Long roleId) {
        userService.addRole(userId, roleId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}