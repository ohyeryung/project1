package com.sparta.project1.service;

import com.sparta.project1.dto.UserRequestDto;
import com.sparta.project1.model.User;
import com.sparta.project1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(UserRequestDto requestDto) {
        String username = requestDto.getUsername();
        //패스워드 암호화
        String password = passwordEncoder.encode(requestDto.getPassword());

        User user = new User(username, password);
        userRepository.save(user);
    }

}