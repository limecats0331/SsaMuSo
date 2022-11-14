package com.ssafy.ssamuso.repository;

import com.ssafy.ssamuso.domain.entity.Techstack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TechstackRepository extends JpaRepository<Techstack, Long> {
    Techstack save(String techName);

    Optional<Techstack> findById(Long id);
}
