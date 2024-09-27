package org.rakibhasan.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.rakibhasan.blog.entities.Role;
import org.rakibhasan.blog.entities.User;
import org.rakibhasan.blog.exceptions.ResourceNotFoundException;
import org.rakibhasan.blog.payloads.UserDto;
import org.rakibhasan.blog.repositories.RoleRepository;
import org.rakibhasan.blog.repositories.UserRepository;
import org.rakibhasan.blog.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.rakibhasan.blog.config.AppConstants.ID_NORMAL_USER;


@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper modelmapper;
    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;

    public UserServiceImpl(
            UserRepository userRepository,
            ModelMapper modelmapper,
            PasswordEncoder passwordEncoder,
            RoleRepository roleRepository
    ) {
        this.userRepository = userRepository;
        this.modelmapper = modelmapper;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDto registerUser(UserDto userDto) {
        User user = modelmapper.map(userDto, User.class);

        // ENCODED THE PASSWORD
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // ROLE
        Role role = this.roleRepository.findById(ID_NORMAL_USER)
                .orElseThrow(() -> new ResourceNotFoundException("Role", ID_NORMAL_USER));
        user.getRoles().add(role);
        User saveUser = this.userRepository.save(user);

        return this.modelmapper.map(saveUser, UserDto.class);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.dtoToUser(userDto);
        User savedUser = this.userRepository.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = this.userRepository.findById(userId).orElseThrow((
                () -> new ResourceNotFoundException(userDto.getName(), userId)
        ));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        User updatedUser = this.userRepository.save(user);
        return userToDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = this.userRepository.findById(userId).orElseThrow((
                () -> new ResourceNotFoundException(userId)
        ));
        return userToDto(user);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = this.userRepository.findByEmail(email).orElseThrow((
                () -> new ResourceNotFoundException(email)
        ));
        return userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = this.userRepository.findAll();
        return users.stream().map(this::userToDto).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = this.userRepository.findById(userId).orElseThrow((
                () -> new ResourceNotFoundException(userId)
        ));
        this.userRepository.delete(user);
    }

    private User dtoToUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        return user;
    }

    private UserDto userToDto(User user) {
        UserDto userDot = new UserDto();
        userDot.setId(user.getId());
        userDot.setName(user.getName());
        userDot.setEmail(user.getEmail());
        userDot.setPassword(user.getPassword());
        userDot.setAbout(user.getAbout());
        return userDot;
    }

}
