package com.ssafy.ssamuso.users.repository;

import com.ssafy.ssamuso.users.domain.Portfolios;
import com.ssafy.ssamuso.users.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface PortfoliosRepository extends JpaRepository<Portfolios, Long> {
    @Transactional
    Portfolios save(Portfolios portfolios);

    Optional<Portfolios> findByUser(User user);
}
