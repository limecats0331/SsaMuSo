package com.ssafy.ssamuso.repository;

import com.ssafy.ssamuso.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    User save(User user);

    void deleteById(Long id);
}
