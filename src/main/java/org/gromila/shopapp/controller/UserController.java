package org.gromila.shopapp.controller;

import lombok.RequiredArgsConstructor;
import org.gromila.shopapp.dto.UserCreateDto;
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
    public Long create(@RequestBody UserCreateDto userCreateDto) {
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
    public UserDto update(@PathVariable Long id,
                          @RequestParam String name,
                          @RequestParam String surname,
                          @RequestParam String login,
                          @RequestParam String password) {
        return userService.update(id, name, surname, login, password);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}