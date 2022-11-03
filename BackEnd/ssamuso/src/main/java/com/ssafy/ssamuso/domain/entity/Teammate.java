package com.ssafy.ssamuso.domain.entity;

import com.ssafy.ssamuso.users.domain.User;
import com.ssafy.ssamuso.domain.entity.enumtype.TeamRole;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Teammate {

    @Id
    @GeneratedValue
    @Column(name = "teammate_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TeamRole role;

    private Integer state;
}
