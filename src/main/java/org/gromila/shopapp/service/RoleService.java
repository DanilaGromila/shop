package org.gromila.shopapp.service;

import lombok.RequiredArgsConstructor;
import org.gromila.shopapp.dto.RoleDto;
import org.gromila.shopapp.entity.Authority;
import org.gromila.shopapp.entity.Role;
import org.gromila.shopapp.exception.ApplicationException;
import org.gromila.shopapp.mapper.RoleMapper;
import org.gromila.shopapp.repository.AuthorityRepository;
import org.gromila.shopapp.repository.RoleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    private final AuthorityRepository authorityRepository;

    @Transactional
    public Long create(String name) {
        Role role = new Role();
        role.setName(name);
        return roleRepository.save(role).getId();
    }

    @Transactional(readOnly = true)
    public RoleDto findById(Long id) {
        Role role = roleRepository.findById(id).
                orElseThrow(() -> new ApplicationException("Role not found", HttpStatus.NOT_FOUND));
        return roleMapper.toDto(role);
    }

    @Transactional(readOnly = true)
    public List<RoleDto> findAll() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream().map(roleMapper::toDto).toList();
    }

    @Transactional
    public void addAuthority(Long roleId, Long authorityId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ApplicationException("Role not found", HttpStatus.NOT_FOUND));
        Authority authority = authorityRepository.findById(authorityId)
                .orElseThrow(() -> new ApplicationException("Authority not found", HttpStatus.NOT_FOUND));
        role.getAuthorities().add(authority);
        roleRepository.save(role);
    }

    @Transactional
    public void update(Long id, String newName) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("Role not found", HttpStatus.NOT_FOUND));
        role.setName(newName);
        roleRepository.save(role);
    }

    @Transactional
    public void delete(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("Role not found", HttpStatus.NOT_FOUND));
        roleRepository.delete(role);
    }
}
