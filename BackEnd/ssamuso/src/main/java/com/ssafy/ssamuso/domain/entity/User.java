package com.ssafy.ssamuso.domain.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements Serializable {

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
}
