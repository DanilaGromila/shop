package org.gromila.shopapp.service;

import lombok.RequiredArgsConstructor;
import org.gromila.shopapp.dto.RoleDto;
import org.gromila.shopapp.entity.Role;
import org.gromila.shopapp.mapper.RoleMapper;
import org.gromila.shopapp.repository.RoleRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper = Mappers.getMapper(RoleMapper.class);

    public Long create(String name) {
        return roleRepository.create(name);
    }

    public RoleDto findById(Long id) {
        Role role = roleRepository.findById(id);
        return roleMapper.toDto(role);
    }

    public List<RoleDto> findAll() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream().map(roleMapper::toDto).toList();
    }

    public void addAuthority(Long roleId, Long authorityId) {
        Role role = roleRepository.findById(roleId);
        roleRepository.addAuthority(role, authorityId);
    }

    public void delete(Long id) {
        roleRepository.delete(id);
    }
}
