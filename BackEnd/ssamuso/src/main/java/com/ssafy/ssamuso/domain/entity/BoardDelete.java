package com.ssafy.ssamuso.domain.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardDelete {

    @Id
    @GeneratedValue
    @Column(name = "board_delete_id")
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 5000)
    private String content;

    @CreatedDate
    private LocalDate uploadDate;

    @ColumnDefault("0")
    private Integer beMax;

    @ColumnDefault("0")
    private Integer feMax;

    @ColumnDefault("0")
    private Integer appMax;

    @ColumnDefault("0")
    private Integer embMax;

    @ColumnDefault("0")
    private Integer beNow;

    @ColumnDefault("0")
    private Integer feNow;

    @ColumnDefault("0")
    private Integer appNow;

    @ColumnDefault("0")
    private Integer embNow;

    @Column(length = 20, nullable = false)
    private String username;

    @Column(nullable = false)
    private LocalDate deadline;

    @ColumnDefault("0")
    private Integer state;

    public BoardDelete(Board board){
        this.id=board.getId();
        this.title = board.getTitle();
        this.content= board.getContent();
        this.uploadDate=board.getUploadDate();
        this.beMax=board.getBeMax();
        this.feMax =board.getFeMax();
        this.appMax= board.getAppMax();
        this.embMax = board.getEmbMax();
        this.beNow =board.getBeNow();
        this.feNow = board.getFeNow();
        this.appNow =board.getAppNow();
        this.embNow = board.getEmbNow();
        this.username = board.getName();
        this.deadline = board.getDeadline();
        this.state = board.getState();
    }
}
