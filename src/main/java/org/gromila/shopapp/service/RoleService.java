package org.gromila.shopapp.service;

import lombok.RequiredArgsConstructor;
import org.gromila.shopapp.dto.RoleDto;
import org.gromila.shopapp.entity.Authority;
import org.gromila.shopapp.entity.Role;
import org.gromila.shopapp.mapper.RoleMapper;
import org.gromila.shopapp.repository.AuthorityRepository;
import org.gromila.shopapp.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    private final AuthorityRepository authorityRepository;

    public Long create(String name) {
        Role role = new Role();
        role.setName(name);
        return roleRepository.save(role).getId();
    }

    public RoleDto findById(Long id) {
        Role role = roleRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Role is not fond"));
        return roleMapper.toDto(role);
    }

    public List<RoleDto> findAll() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream().map(roleMapper::toDto).toList();
    }

    @Transactional
    public void addAuthority(Long roleId, Long authorityId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role is not found"));
        Authority authority = authorityRepository.findById(authorityId)
                .orElseThrow(() -> new RuntimeException("Authority is not found"));
        role.getAuthorities().add(authority);
        roleRepository.save(role);
    }

    @Transactional
    public void update(Long id, String newName) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role is not found"));
        role.setName(newName);
        roleRepository.save(role);
    }

    public void delete(Long id) {
        roleRepository.deleteById(id);
    }
}
