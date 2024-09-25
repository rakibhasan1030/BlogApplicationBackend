package org.rakibhasan.blog.security;

import org.rakibhasan.blog.entities.User;
import org.rakibhasan.blog.exceptions.ResourceNotFoundException;
import org.rakibhasan.blog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private UserRepository userRepository;
    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // LOADING USER FROM DATABASE BY USERNAME
        return this.userRepository.findByEmail(username).orElseThrow((
                () -> new ResourceNotFoundException(username)
        ));
    }
}
