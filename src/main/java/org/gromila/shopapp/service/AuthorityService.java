package org.gromila.shopapp.service;

import org.gromila.shopapp.dto.AuthorityDto;
import org.gromila.shopapp.entity.Authority;
import org.gromila.shopapp.mapper.AuthorityMapper;
import org.gromila.shopapp.repository.AuthorityRepository;

import java.util.List;

public class AuthorityService {
    private final AuthorityRepository authorityRepository;
    private final AuthorityMapper authorityMapper;

    public AuthorityService(AuthorityRepository authorityRepository, AuthorityMapper authorityMapper) {
        this.authorityRepository = authorityRepository;
        this.authorityMapper = authorityMapper;
    }

    public Long create(String name) {
        return authorityRepository.create(name);
    }

    public AuthorityDto findById(Long id) {
        Authority authority = authorityRepository.findById(id);
        return authorityMapper.mapToDto(authority);
    }

    public List<AuthorityDto> findAll() {
        List<Authority> authorities = authorityRepository.findAll();
        return authorities.stream().map(authority -> authorityMapper.mapToDto(authority)).toList();
    }

    public void delete(Long id) {
        authorityRepository.delete(id);
    }
}
