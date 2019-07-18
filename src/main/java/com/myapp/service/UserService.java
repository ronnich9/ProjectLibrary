package com.myapp.service;

import com.myapp.dto.UserDTO;
import com.myapp.entity.RoleType;
import com.myapp.entity.User;
import com.myapp.exception.DontExistException;
import com.myapp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveNewUser (UserDTO userDTO){

        User localUser = userRepository.findByUsername(userDTO.getUsername());
        if (localUser != null) {
            throw new DontExistException("This account already exist in system");

        }
        User user = new User(
                userDTO.getUsername(),
                passwordEncoder.encode(userDTO.getPassword()),
                userDTO.getPhone_number(),
                userDTO.getEmail(), RoleType.ROLE_USER);

        userRepository.save(user);

    }



}
