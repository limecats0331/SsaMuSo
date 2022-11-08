package com.ssafy.ssamuso.domain.entity;

import com.ssafy.ssamuso.domain.dto.BoardDto;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Page;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Map;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Board {

    @Id
    @GeneratedValue
    @Column(name = "board_id")
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * 실명
     */
    @Column(length = 20, nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate deadline;

    @ColumnDefault("0")
    private Integer state;

    public static Page<BoardDto> convert(Page<Board> boardPage) {

        return boardPage.map(BoardDto::new);
    }

    public Board(BoardDto boardDto) {
        this.id = boardDto.getId();
        this.title = boardDto.getTitle();
        this.content = boardDto.getContent();
        this.uploadDate = boardDto.getUploadDate();
        this.beMax = boardDto.getBeMax();
        this.feMax = boardDto.getFeMax();
        this.appMax = boardDto.getAppMax();
        this.beMax = boardDto.getBeMax();
        this.embMax = boardDto.getEmbMax();
        this.beNow = boardDto.getBeNow();
        this.feNow = boardDto.getFeNow();
        this.appNow = boardDto.getAppNow();
        this.embNow = boardDto.getEmbNow();
        this.name = boardDto.getName();
        this.deadline = boardDto.getDeadline();
        this.state = boardDto.getState();
    }





    public Board(Map map) {
        this.title = (String) map.get("title");
        this.content = (String) map.get("content");
        this.beMax = Integer.parseInt((String) map.get("beMax"));
        this.feMax = Integer.parseInt((String) map.get("feMax"));
        this.appMax = Integer.parseInt((String) map.get("appMax"));
        this.beMax = Integer.parseInt((String) map.get("beMax"));
        this.embMax = Integer.parseInt((String) map.get("embMax"));
        this.beNow = Integer.parseInt((String) map.get("beNow"));
        this.feNow = Integer.parseInt((String) map.get("feNow"));
        this.appNow = Integer.parseInt((String) map.get("appNow"));
        this.embNow = Integer.parseInt((String) map.get("embNow"));
        this.name = (String) map.get("name");
        this.deadline = LocalDate.parse((String) map.get("deadline"));
        this.state = Integer.parseInt((String) map.get("state"));
    }

    public static Board revise(Board board, BoardDto boardDto) {

        board.setId(boardDto.getId());
        board.setTitle(boardDto.getTitle());
        board.setContent(boardDto.getContent());
        board.setUploadDate(boardDto.getUploadDate());
        board.setBeMax(boardDto.getBeMax());
        board.setFeMax(boardDto.getFeMax());
        board.setAppMax(boardDto.getAppMax());
        board.setEmbMax(boardDto.getEmbMax());
        board.setBeNow(boardDto.getBeNow());
        board.setFeNow(boardDto.getFeNow());
        board.setAppNow(boardDto.getAppNow());
        board.setEmbNow(boardDto.getEmbNow());
        board.setName(boardDto.getName());
        board.setDeadline(boardDto.getDeadline());
        board.setState(boardDto.getState());

        return board;
    }
}
