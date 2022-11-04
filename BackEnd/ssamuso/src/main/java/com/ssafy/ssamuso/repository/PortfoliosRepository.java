package com.ssafy.ssamuso.repository;

import com.ssafy.ssamuso.domain.entity.Portfolios;
import com.ssafy.ssamuso.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PortfoliosRepository extends JpaRepository<Portfolios, Long> {
//    @Transactional
    Portfolios save(Portfolios portfolios);

    Optional<Portfolios> findByUser(User user);
}
