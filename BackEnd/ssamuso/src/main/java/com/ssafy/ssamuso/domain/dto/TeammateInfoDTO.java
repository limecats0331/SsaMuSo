package com.ssafy.ssamuso.domain.dto;

import com.ssafy.ssamuso.domain.entity.enumtype.TechName;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TeammateInfoDTO {
    private String username;
    private List<TechName> techstacks;
    private String portfoliosLink;

    protected TeammateInfoDTO() {
    }
}
