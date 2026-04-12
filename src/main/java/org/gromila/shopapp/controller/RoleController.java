package org.gromila.shopapp.controller;

import lombok.RequiredArgsConstructor;
import org.gromila.shopapp.dto.RoleDto;
import org.gromila.shopapp.service.RoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @PostMapping
    public Long create(@RequestBody String name) {
        return roleService.create(name);
    }

    @GetMapping("/{id}")
    public RoleDto findById(@PathVariable Long id) {
        return roleService.findById(id);
    }

    @GetMapping
    public List<RoleDto> findAll() {
        return roleService.findAll();
    }

    @PostMapping("/{id}/authorities/{authorityId}")
    public void addAuthority(@PathVariable Long id, @PathVariable Long authorityId) {
        roleService.addAuthority(id, authorityId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        roleService.delete(id);
    }
}
