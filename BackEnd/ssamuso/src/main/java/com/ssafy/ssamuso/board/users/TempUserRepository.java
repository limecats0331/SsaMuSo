package com.ssafy.ssamuso.board.users;

import com.ssafy.ssamuso.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TempUserRepository extends JpaRepository<User,Long> {

    @Query(value = "select n.id from User n where n.username = :username")
    Optional<User> findByUsername(String username);

}
