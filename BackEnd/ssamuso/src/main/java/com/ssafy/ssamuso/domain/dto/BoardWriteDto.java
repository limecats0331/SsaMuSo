package com.ssafy.ssamuso.domain.dto;

import com.ssafy.ssamuso.domain.entity.enumtype.TechName;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@ToString
@EqualsAndHashCode
public class BoardWriteDto {
    private String title;
    private String content;
    private Integer beMax;
    private Integer feMax;
    private Integer appMax;
    private Integer embMax;
    private Integer beNow;
    private Integer feNow;
    private Integer appNow;
    private Integer embNow;
    private String name;
    private LocalDate deadline;
    private Integer state;
    private List<TechName> techNames;

}
