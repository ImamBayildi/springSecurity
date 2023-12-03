package com.labreport.backendlabrep.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.labreport.backendlabrep.dto.CreateUserRequest;
import com.labreport.backendlabrep.entity.User;
import com.labreport.backendlabrep.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SecurityService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        return user.orElseThrow(EntityNotFoundException::new);
    }
    // @Override//by Codeium
    // public org.springframework.security.core.userdetails.UserDetails
    // loadUserByUsername(String username) throws
    // org.springframework.security.core.userdetails.UsernameNotFoundException {
    // Optional<User> user = userRepository.findByUsername(username);
    // return user.orElseThrow(EntityNotFoundException::new);
    // }

    public Optional<User> getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User createUser(CreateUserRequest req) {
        User newUser = User.builder()
                .name(req.name())
                .username(req.username())
                .password(bCryptPasswordEncoder.encode(req.password()))
                .authorities(req.authorities())
                .accountNonExpired(true)
                .credentialsNonExpired(true)
                .isEnabled(true)
                .accountNonLocked(true)
                .build();
        return userRepository.save(newUser);
    }

}
