package org.gromila.shopapp.service;

import lombok.RequiredArgsConstructor;
import org.gromila.shopapp.dto.AuthorityDto;
import org.gromila.shopapp.entity.Authority;
import org.gromila.shopapp.mapper.AuthorityMapper;
import org.gromila.shopapp.repository.AuthorityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorityService {
    private final AuthorityRepository authorityRepository;
    private final AuthorityMapper authorityMapper;


    public Long create(String name) {
        Authority authority = new Authority();
        authority.setName(name);
        return authorityRepository.save(authority).getId();
    }

    public AuthorityDto findById(Long id) {
        Authority authority = authorityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Authority no found"));
        return authorityMapper.toDto(authority);
    }

    public List<AuthorityDto> findAll() {
        List<Authority> authorities = authorityRepository.findAll();
        return authorities.stream().map(authorityMapper::toDto).toList();
    }

    @Transactional
    public void delete(Long id) {
        Authority authority = authorityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Authority no found"));
        authorityRepository.delete(authority);
    }
}
