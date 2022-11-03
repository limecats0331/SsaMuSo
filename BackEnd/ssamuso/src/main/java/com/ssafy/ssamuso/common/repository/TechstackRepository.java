package com.ssafy.ssamuso.common.repository;

import com.ssafy.ssamuso.common.domain.Techstack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TechstackRepository extends JpaRepository<Techstack, Long> {
    Techstack save(String techName);

    Optional<Techstack> findById(Long id);
}
