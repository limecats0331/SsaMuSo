package com.ssafy.ssamuso.domain.dto;

import com.ssafy.ssamuso.domain.entity.enumtype.TeamRole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MyTeamDTO {
    private String username;
    private Long boardId;
    protected String boardTitle;
    private int state;
    private TeamRole role;
}
