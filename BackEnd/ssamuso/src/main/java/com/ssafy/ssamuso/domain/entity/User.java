package com.ssafy.ssamuso.domain.entity;

import com.ssafy.ssamuso.domain.entity.enumtype.Role;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
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

    @Column(length = 10)
    private String area;

    @Column(nullable = false)
    private Integer term;

    @Column(length = 20)
    private String track;

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

    public User(String username, String name, String email, String password, PasswordEncoder passwordEncoder) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.role = Role.USER;
        this.password = passwordEncoder.encode(password);
    }
}
