package org.gromila.shopapp.service;

import lombok.RequiredArgsConstructor;
import org.gromila.shopapp.annotation.CacheEvict;
import org.gromila.shopapp.annotation.Cached;
import org.gromila.shopapp.dto.AuthorityDto;
import org.gromila.shopapp.entity.Authority;
import org.gromila.shopapp.exception.ApplicationException;
import org.gromila.shopapp.mapper.AuthorityMapper;
import org.gromila.shopapp.repository.AuthorityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorityService {
    private final AuthorityRepository authorityRepository;
    private final AuthorityMapper authorityMapper;

    @Transactional
    public Long create(String name) {
        Authority authority = new Authority();
        authority.setName(name);
        return authorityRepository.save(authority).getId();
    }

    @Transactional(readOnly = true)
    @Cached(prefix = "authorities", key = "#id")
    public AuthorityDto findById(Long id) {
        Authority authority = authorityRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("Authority not found", HttpStatus.NOT_FOUND));
        return authorityMapper.toDto(authority);
    }

    @Transactional(readOnly = true)
    public List<AuthorityDto> findAll() {
        List<Authority> authorities = authorityRepository.findAll();
        return authorities.stream().map(authorityMapper::toDto).toList();
    }

    @Transactional
    @CacheEvict(prefix = "authorities", key = "#id")
    public void delete(Long id) {
        Authority authority = authorityRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("Authority not found", HttpStatus.NOT_FOUND));
        authorityRepository.delete(authority);
    }
}
