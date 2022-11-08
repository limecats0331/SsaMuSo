package com.ssafy.ssamuso.service;

import com.ssafy.ssamuso.domain.entity.User;
import com.ssafy.ssamuso.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImlp {
    private final UserRepository userRepository;

    Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }

    public Optional<User> findByUsername(String name){
        return userRepository.findByUsername(name);
    }
}
