package com.ssafy.ssamuso.repository;

import com.ssafy.ssamuso.domain.entity.Teammate;
import com.ssafy.ssamuso.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeammateRepository extends JpaRepository<Teammate, Long> {

    public Teammate save(Teammate teammate);

    public List<Teammate> findAllByUser(User user);

    public List<User> findAllByBoard_Id(Long boardId);
}
