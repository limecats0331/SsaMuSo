package com.ssafy.ssamuso.users.repository;

import com.ssafy.ssamuso.users.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long userId);

    User save(User user);
}
