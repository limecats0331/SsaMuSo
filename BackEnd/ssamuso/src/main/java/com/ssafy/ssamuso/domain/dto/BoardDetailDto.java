package com.ssafy.ssamuso.domain.dto;

import com.ssafy.ssamuso.domain.entity.Board;
import com.ssafy.ssamuso.domain.entity.enumtype.TechName;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class BoardDetailDto {

    private Long id;
    private String title;
    private String content;
    private LocalDate uploadDate;
    private Integer beMax;
    private Integer feMax;
    private Integer appMax;
    private Integer embMax;
    private Integer beNow;
    private Integer feNow;
    private Integer appNow;
    private Integer embNow;
    private String name;

    private String profileImg;
    private LocalDate deadline;
    private Integer state;

    private List<TechName> techNames;

    private List<String> imgUrls;
    public BoardDetailDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.uploadDate = board.getUploadDate();
        this.beMax = board.getBeMax();
        this.feMax = board.getFeMax();
        this.appMax = board.getAppMax();
        this.beMax = board.getBeMax();
        this.embMax = board.getEmbMax();
        this.beNow = board.getBeNow();
        this.feNow = board.getFeNow();
        this.appNow = board.getAppNow();
        this.embNow = board.getEmbNow();
        this.name = board.getName();
        this.deadline = board.getDeadline();
        this.state = board.getState();

    }
}
