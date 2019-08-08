package com.myapp.service;
import com.myapp.dto.UserDTO;
import com.myapp.entity.Role;
import com.myapp.entity.User;

import com.myapp.exception.DontExistException;
import com.myapp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Slf4j
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveNewUser (UserDTO userDTO)  {

        User localUser = userRepository.findByUsername(userDTO.getUsername());
        if (localUser != null) {
            throw new DontExistException("This account already exist in system");

        }
        User user = User.builder()
                .username(userDTO.getUsername())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .phone_number(userDTO.getPhone_number())
                .email(userDTO.getEmail())
                .enabled(true)
                .roles(Collections.singleton(Role.ADMIN))
                .build();

        userRepository.save(user);

    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
}
