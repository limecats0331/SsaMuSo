package com.ssafy.ssamuso.service;

import com.ssafy.ssamuso.domain.dto.BoardDto;
import com.ssafy.ssamuso.domain.entity.Board;
import com.ssafy.ssamuso.domain.entity.enumtype.TechName;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;

public interface BoardService {

    Page<BoardDto> getList(Pageable pageable);

    Page<BoardDto> getListByTags(ArrayList<TechName> techNames, Pageable pageable);

    BoardDto getBoardDto(Long id);

    Board getBoard(Long id);

    Board insert(Board board);

    int deleteBoard(Long id);



}
