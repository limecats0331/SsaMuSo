package com.ssafy.ssamuso.domain.entity;

import com.ssafy.ssamuso.domain.entity.enumtype.Role;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Column(length = 20, nullable = false)
    private String username;

    @Column(length = 50, nullable = false)
    private String email;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 10)
    private String area;

    private Integer term;

    @Column(length = 20)
    private String track;

    private Integer classNum;

    @Column(length = 45)
    private String profileImg;

    @Column(length = 100, nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private Role role;

    public User(String username, String name, String email, String password, PasswordEncoder passwordEncoder) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.role = Role.USER;
        this.password = passwordEncoder.encode(password);
    }
}
