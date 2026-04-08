package org.gromila.shopapp.controller;

import lombok.RequiredArgsConstructor;
import org.gromila.shopapp.dto.AuthorityDto;
import org.gromila.shopapp.service.AuthorityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authorities")
@RequiredArgsConstructor
public class AuthorityController {
    private final AuthorityService authorityService;

    @PostMapping
    public Long create(@RequestBody String name) {//работает
        return authorityService.create(name);
    }

    @GetMapping
    public List<AuthorityDto> findAll() {//работает
        return authorityService.findAll();
    }

    @GetMapping("{id}")
    public AuthorityDto findById(@PathVariable Long id) {//работает
        return authorityService.findById(id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {//работает
        authorityService.delete(id);
    }
}
