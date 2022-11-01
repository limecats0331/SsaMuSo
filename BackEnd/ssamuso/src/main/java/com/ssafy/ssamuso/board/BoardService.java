package com.ssafy.ssamuso.board;

import com.ssafy.ssamuso.domain.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BoardService {

    Page<Board> getList(Pageable pageable);

    Optional<Board> getBoard(Long id);

    Board insert(Board board);

    int deleteBoard(Long id);



}
