package com.ssafy.ssamuso.board.boardDto;

import com.ssafy.ssamuso.domain.entity.Board;
import com.ssafy.ssamuso.domain.entity.User;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;

@Getter

public class BoardDto {

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
    private String username;
    private LocalDate deadline;
    private Integer state;

    public BoardDto(Board board){
        this.id=board.getId();
        this.title =board.getTitle();
        this.content = board.getContent();
        this.uploadDate = board.getUploadDate();
        this.beMax =board.getBeMax();
        this.feMax = board.getFeMax();
        this.appMax=board.getAppMax();
        this.beMax =board.getBeMax()    ;
        this.embMax = board.getEmbMax();
        this.beNow = board.getBeNow();
        this.feNow = board.getFeNow();
        this.appNow = board.getAppNow();
        this.embNow = board.getEmbNow();
        this.username = board.getUsername();
        this.deadline = board.getDeadline();
        this.state =board.getState();

    }



}
