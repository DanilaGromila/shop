package org.gromila.shopapp.service;

import org.gromila.shopapp.dto.RoleDto;
import org.gromila.shopapp.entity.Role;
import org.gromila.shopapp.mapper.RoleMapper;
import org.gromila.shopapp.repository.RoleRepository;

import java.util.List;

public class RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleService(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    public Long create(String name) {
        return roleRepository.create(name);
    }

    public RoleDto findById(Long id) {
        Role role = roleRepository.findById(id);
        return roleMapper.mapToDto(role);
    }

    public List<RoleDto> findAll() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream().map(role -> roleMapper.mapToDto(role)).toList();
    }

    public void addAuthority(Long roleId, Long authorityId){
        Role role = roleRepository.findById(roleId);
        roleRepository.addAuthority(role, authorityId);
    }

    public void delete(Long id) {
        roleRepository.delete(id);
    }
}
