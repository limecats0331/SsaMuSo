package com.ssafy.ssamuso.service;

import com.ssafy.ssamuso.domain.entity.Teammate;

import java.util.List;

public interface TeamService {
    List<Teammate> findTeammateByUsername(String username);

    List<Teammate> findTeammateByBoardId(Long boardId);
}
