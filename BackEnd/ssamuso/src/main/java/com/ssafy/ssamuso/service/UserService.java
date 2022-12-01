package com.ssafy.ssamuso.service;

import com.ssafy.ssamuso.domain.dto.TeammateInfoDTO;
import com.ssafy.ssamuso.domain.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findById(Long userId);

    Optional<User> findByUsername(String name);

    Optional<TeammateInfoDTO> findTeammateByUsername(String username);

    void deleteUser(String username);
}
