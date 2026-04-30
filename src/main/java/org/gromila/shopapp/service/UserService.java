package org.gromila.shopapp.service;

import lombok.RequiredArgsConstructor;
import org.gromila.shopapp.annotation.CacheEvict;
import org.gromila.shopapp.annotation.CachePut;
import org.gromila.shopapp.annotation.Cached;
import org.gromila.shopapp.dto.UserCreateUpdateDto;
import org.gromila.shopapp.dto.UserDto;
import org.gromila.shopapp.entity.Role;
import org.gromila.shopapp.entity.User;
import org.gromila.shopapp.exception.ApplicationException;
import org.gromila.shopapp.mapper.UserMapper;
import org.gromila.shopapp.repository.RoleRepository;
import org.gromila.shopapp.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    @Transactional
    public Long create(UserCreateUpdateDto createdUser) {
        User user = userMapper.toEntity(createdUser);
        return userRepository.save(user).getId();
    }

    @Transactional
    public void addRole(Long userId, Long roleId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApplicationException("User not found", HttpStatus.NOT_FOUND));
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new ApplicationException("Role not found", HttpStatus.NOT_FOUND));
        user.getRoles().add(role);
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    @Cached(prefix = "users", key = "#id")
    public UserDto findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("User not found", HttpStatus.NOT_FOUND));
        return userMapper.toDto(user);
    }

    @Transactional(readOnly = true)
    public List<UserDto> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Transactional
    @CachePut(prefix = "users", key = "#id")
    public UserDto update(Long id, UserCreateUpdateDto userCreateUpdateDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("User not found", HttpStatus.NOT_FOUND));
        user.setName(userCreateUpdateDto.getName());
        user.setSurname(userCreateUpdateDto.getSurname());
        user.setLogin(userCreateUpdateDto.getLogin());
        user.setPassword(userCreateUpdateDto.getPassword());
        User updatedUser = userRepository.save(user);
        return userMapper.toDto(updatedUser);
    }

    @Transactional
    @CacheEvict(prefix = "users", key = "#id")
    public void delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("User not found", HttpStatus.NOT_FOUND));
        userRepository.delete(user);
    }
}
