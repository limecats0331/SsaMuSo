package com.ssafy.ssamuso.domain.entity;

import com.ssafy.ssamuso.users.domain.Techstack;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

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

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "techstack_id")
    private Techstack techstack;
}
