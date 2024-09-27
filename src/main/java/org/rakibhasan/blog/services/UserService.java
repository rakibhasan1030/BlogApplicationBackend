package org.rakibhasan.blog.services;

import org.rakibhasan.blog.payloads.UserDto;

import java.util.List;

public interface UserService {

    // create
    UserDto registerUser(UserDto userDto);

    // create
    UserDto createUser(UserDto userDto);

    // update
    UserDto updateUser(UserDto userDto, Integer userId);

    // get single user by id
    UserDto getUserById(Integer userId);

    // get single user by email
    UserDto getUserByEmail(String email);

    // get all user
    List<UserDto> getAllUsers();

    // delete user by id
    void deleteUser(Integer userId);

}
