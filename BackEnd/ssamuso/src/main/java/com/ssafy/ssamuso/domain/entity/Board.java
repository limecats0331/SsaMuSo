package com.ssafy.ssamuso.domain.entity;

import com.ssafy.ssamuso.domain.dto.BoardDetailDto;
import com.ssafy.ssamuso.domain.dto.BoardDto;
import com.ssafy.ssamuso.domain.entity.enumtype.TechName;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Page;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
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

    @OneToMany(mappedBy = "board")
    private List<BoardTechstack> boardTechstacks;

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

    public static Page<BoardDto> convert(Page<Board> boardPage, List<List<TechName>> techNameLists, List<List<String>> imgs, List<String> profileImgs) {
        Iterator imgIter = imgs.iterator();
        Iterator techIter = techNameLists.iterator();
        Iterator profileIter = profileImgs.iterator();
        Page<BoardDto> boardDtoList = boardPage.map(board -> {
            return BoardDto.builder()
                    .id(board.getId())
                    .title(board.getTitle())
                    .uploadDate(board.getUploadDate())
                    .name(board.getName())
                    .deadline(board.getDeadline())
                    .state(board.getState())
                    .beMax(board.getBeMax())
                    .feMax(board.getFeMax())
                    .appMax(board.getAppMax())
                    .embMax(board.getEmbMax())
                    .beNow(board.getBeNow())
                    .feNow(board.getFeNow())
                    .appNow(board.getAppNow())
                    .embNow(board.getEmbNow())
                    .imgUrls((List<String>) imgIter.next())
                    .techNames((List<TechName>) techIter.next())
                    .profileImg((String) profileIter.next())
                    .build();

        });
        return boardDtoList;
    }

    public Board(BoardDto boardDto) {
        this.id = boardDto.getId();
        this.title = boardDto.getTitle();
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

    public static Board revise(Board board, BoardDetailDto boardDetailDto) {

        board.setId(boardDetailDto.getId());
        board.setTitle(boardDetailDto.getTitle());
        board.setContent(boardDetailDto.getContent());
        board.setUploadDate(boardDetailDto.getUploadDate());
        board.setBeMax(boardDetailDto.getBeMax());
        board.setFeMax(boardDetailDto.getFeMax());
        board.setAppMax(boardDetailDto.getAppMax());
        board.setEmbMax(boardDetailDto.getEmbMax());
        board.setBeNow(boardDetailDto.getBeNow());
        board.setFeNow(boardDetailDto.getFeNow());
        board.setAppNow(boardDetailDto.getAppNow());
        board.setEmbNow(boardDetailDto.getEmbNow());
        board.setName(boardDetailDto.getName());
        board.setDeadline(boardDetailDto.getDeadline());
        board.setState(boardDetailDto.getState());

        return board;
    }

    public static Board revise(Board board, BoardDto boardDto) {

        board.setId(boardDto.getId());
        board.setTitle(boardDto.getTitle());
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
