package com.ssafy.ssamuso.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardTechstack {

    @Id
    @GeneratedValue
    @Column(name = "board_techstack_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "techstack_id")
    private Techstack techstack;
}
