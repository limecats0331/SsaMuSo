package com.ssafy.ssamuso.domain.entity;

import com.ssafy.ssamuso.domain.entity.enumtype.Role;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDelete {

    @Id
    @GeneratedValue
    @Column(name = "user_delete_id")
    private Long id;

    /**
     * {username}@gmail.com or {username}@naver.com
     */
    @Column(length = 120, nullable = false)
    private String username;

    @Column(length = 50, nullable = false)
    private String email;

    /**
     * 실명
     */
    @Column(length = 20, nullable = false)
    private String name;

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

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private Role role;
}
