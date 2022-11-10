package com.ssafy.ssamuso.service;

import com.ssafy.ssamuso.domain.entity.Teammate;

import java.util.List;

public interface TeamService {
    List<Teammate> findTeamByUsername(String username);

    List<Teammate> findTeamByBoardId(Long boardId);
}
