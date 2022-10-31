package com.ssafy.ssamuso.users.domain;

import com.ssafy.ssamuso.users.domain.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Portfolios {

    @Id
    @GeneratedValue
    @Column(name = "portfolios_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(length = 300)
    private String link;
}
