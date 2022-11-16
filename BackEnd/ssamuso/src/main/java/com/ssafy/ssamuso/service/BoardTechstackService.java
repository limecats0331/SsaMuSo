package com.ssafy.ssamuso.service;

import com.ssafy.ssamuso.domain.entity.Board;
import com.ssafy.ssamuso.domain.entity.BoardTechstack;
import com.ssafy.ssamuso.domain.entity.Techstack;
import com.ssafy.ssamuso.domain.entity.enumtype.TechName;

import java.util.List;

public interface BoardTechstackService {

    List<TechName> findByBoard(Board board);

    List<Techstack> save(Board board, List<TechName> techNames);

    int delete(Long boardId);

}
