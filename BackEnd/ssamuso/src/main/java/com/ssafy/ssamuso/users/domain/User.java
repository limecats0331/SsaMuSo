package com.ssafy.ssamuso.users.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Column(length = 20, nullable = false)
    private String username;

    @Column(length = 10, nullable = false)
    private String area;

    @Column(nullable = false)
    private Integer term;

    @Column(length = 20, nullable = false)
    private String track;

    @Column(nullable = false)
    private Integer classNum;

    @Column(length = 45)
    private String profileImg;

    @Column(length = 100, nullable = false)
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserTechstack> userTechstacks;
}
