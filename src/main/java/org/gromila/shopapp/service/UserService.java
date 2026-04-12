package org.gromila.shopapp.service;

import lombok.RequiredArgsConstructor;
import org.gromila.shopapp.dto.UserCreateDto;
import org.gromila.shopapp.dto.UserDto;
import org.gromila.shopapp.entity.Role;
import org.gromila.shopapp.entity.User;
import org.gromila.shopapp.mapper.UserMapper;
import org.gromila.shopapp.repository.RoleRepository;
import org.gromila.shopapp.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    public Long create(UserCreateDto createdUser) {
        User user = userMapper.toEntity(createdUser);
        return userRepository.save(user).getId();
    }

    @Transactional
    public void addRole(Long userId, Long roleId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        user.getRoles().add(role);
        userRepository.save(user);
    }

    public UserDto findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toDto(user);
    }

    public List<UserDto> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Transactional
    public UserDto update(Long id, String name, String surname, String login, String password) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(name);
        user.setSurname(surname);
        user.setLogin(login);
        user.setPassword(password);
        User updatedUser = userRepository.save(user);
        return userMapper.toDto(updatedUser);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
