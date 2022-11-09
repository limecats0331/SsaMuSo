package com.ssafy.ssamuso.domain.dto;

import com.ssafy.ssamuso.domain.entity.enumtype.TechName;
import lombok.Data;

import java.util.List;

@Data
public class TeammateInfoDTO {
    private String username;
    private List<TechName> techstacks;
    private String portfoliosLink;
}
