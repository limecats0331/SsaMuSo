package com.ssafy.ssamuso.board;


import com.ssafy.ssamuso.domain.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface BoardRepository extends JpaRepository<Board,Long> {
    Page<Board> findAll (Pageable pageable);

    Optional<Board> findById(Long id);

}
