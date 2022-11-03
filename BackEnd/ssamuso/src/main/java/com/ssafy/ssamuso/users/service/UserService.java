package com.ssafy.ssamuso.users.service;

import com.ssafy.ssamuso.users.domain.User;
import com.ssafy.ssamuso.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }
}
