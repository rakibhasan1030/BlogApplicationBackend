package org.rakibhasan.blog.services;

import org.rakibhasan.blog.payloads.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto userDto, Integer userId);
    UserDto getUserById(Integer userId);
    UserDto getUserByEmail(String email);
    List<UserDto> getAllUsers();
    void deleteUser(Integer userId);
}
