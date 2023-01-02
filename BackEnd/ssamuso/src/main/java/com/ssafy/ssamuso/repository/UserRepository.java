package com.ssafy.ssamuso.repository;

import com.ssafy.ssamuso.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    User save(User user);

    void deleteById(Long id);

    @Query("select bl.routingKey from User u join u.boardList bl where u.username = :username")
    List<String> findRoutingKeyByUsername(@Param("username") String username);
}
