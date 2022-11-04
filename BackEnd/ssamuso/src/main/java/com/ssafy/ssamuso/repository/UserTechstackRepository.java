package com.ssafy.ssamuso.repository;

import com.ssafy.ssamuso.domain.entity.User;
import com.ssafy.ssamuso.domain.entity.UserTechstack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserTechstackRepository extends JpaRepository<UserTechstack, Long> {
    UserTechstack save(UserTechstack userTechstack);

    Optional<List<UserTechstack>> findAllByUser(User user);
}
