package com.ssafy.ssamuso.repository;

import com.ssafy.ssamuso.domain.entity.Board;
import com.ssafy.ssamuso.domain.entity.BoardTechstack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface BoardTechstackRepository extends JpaRepository<BoardTechstack, Long> {
    BoardTechstack save(BoardTechstack boardTechstack);
    ArrayList<BoardTechstack> findByBoard(Board board);
}
