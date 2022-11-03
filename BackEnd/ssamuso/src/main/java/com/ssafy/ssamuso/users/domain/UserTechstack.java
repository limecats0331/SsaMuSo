package com.ssafy.ssamuso.users.domain;

import com.ssafy.ssamuso.common.domain.Techstack;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserTechstack {

    @Id
    @GeneratedValue
    @Column(name = "user_techstack_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "techstack_id")
    private Techstack techstack;
}
