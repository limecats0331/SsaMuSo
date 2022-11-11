package com.ssafy.ssamuso.repository;

import com.ssafy.ssamuso.domain.entity.UserDelete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDeleteRepository extends JpaRepository<UserDelete, Long> {
    UserDelete save(UserDelete userDelete);
}
