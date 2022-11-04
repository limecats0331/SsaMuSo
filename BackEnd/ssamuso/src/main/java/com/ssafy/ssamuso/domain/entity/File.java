package com.ssafy.ssamuso.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class File {

    @Id
    @GeneratedValue
    @Column(name = "file_id")
    private Long id;

    @Column(length = 300, nullable = false)
    private String originalName;

    @Column(length = 300, nullable = false)
    private String changedName;

    @Column(length = 300, nullable = false)
    private String url;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;
}
