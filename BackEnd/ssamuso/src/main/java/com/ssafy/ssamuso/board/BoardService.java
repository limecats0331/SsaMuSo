package com.ssafy.ssamuso.board;

import com.ssafy.ssamuso.board.boardDto.BoardDto;
import com.ssafy.ssamuso.domain.entity.Board;
import com.ssafy.ssamuso.domain.entity.enumtype.TechName;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Optional;

public interface BoardService {

    Page<BoardDto> getList(Pageable pageable);

    Page<Board> getListByTags(ArrayList<TechName> techNames, Pageable pageable);

    Board getBoard(Long id);

    Board insert(Board board);

    int deleteBoard(Long id);



}
