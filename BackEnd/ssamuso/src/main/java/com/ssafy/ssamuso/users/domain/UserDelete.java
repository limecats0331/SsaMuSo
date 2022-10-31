package com.ssafy.ssamuso.users.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
}
