package com.ssafy.ssamuso.repository;

import com.ssafy.ssamuso.domain.entity.Board;
import com.ssafy.ssamuso.domain.entity.BoardTechstack;
import com.ssafy.ssamuso.domain.entity.Techstack;
import com.ssafy.ssamuso.domain.entity.enumtype.TechName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardTechstackRepository extends JpaRepository<BoardTechstack, Long> {

    List<BoardTechstack> findByBoard(Board board);
    BoardTechstack save(BoardTechstack boardTechstack);


}
