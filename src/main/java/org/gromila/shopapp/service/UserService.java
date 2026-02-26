package org.gromila.shopapp.service;

import org.gromila.shopapp.dto.UserCreateDto;
import org.gromila.shopapp.dto.UserDto;
import org.gromila.shopapp.entity.User;
import org.gromila.shopapp.mapper.UserMapper;
import org.gromila.shopapp.repository.UserRepository;

import java.util.List;

public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public Long create(UserCreateDto createdUser) {
        User user = userMapper.mapToEntity(createdUser);
        return userRepository.create(user);
    }

    public void addRole(Long userId, Long roleId){
        User user = userRepository.findById(userId);
        userRepository.addRole(user, roleId);
    }


    public UserDto findById(Long id) {
        User user = userRepository.findById(id);
        return userMapper.mapToDto(user);
    }

    public List<UserDto> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> userMapper.mapToDto(user)).toList();
    }

    public void update(Long id, String name, String surname, String login, String password) {
        userRepository.update(id, name, surname, login, password);
    }

    public void delete(Long id) {
        userRepository.delete(id);
    }
}
