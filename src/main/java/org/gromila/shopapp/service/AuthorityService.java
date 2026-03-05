package org.gromila.shopapp.service;

import lombok.RequiredArgsConstructor;
import org.gromila.shopapp.dto.AuthorityDto;
import org.gromila.shopapp.entity.Authority;
import org.gromila.shopapp.mapper.AuthorityMapper;
import org.gromila.shopapp.repository.AuthorityRepository;
import org.mapstruct.factory.Mappers;

import java.util.List;

@RequiredArgsConstructor
public class AuthorityService {
    private final AuthorityRepository authorityRepository;
    private final AuthorityMapper authorityMapper = Mappers.getMapper(AuthorityMapper.class) ;

    public Long create(String name) {
        return authorityRepository.create(name);
    }

    public AuthorityDto findById(Long id) {
        Authority authority = authorityRepository.findById(id);
        return authorityMapper.toDto(authority);
    }

    public List<AuthorityDto> findAll() {
        List<Authority> authorities = authorityRepository.findAll();
        return authorities.stream().map(authorityMapper::toDto).toList();
    }

    public void delete(Long id) {
        authorityRepository.delete(id);
    }
}
