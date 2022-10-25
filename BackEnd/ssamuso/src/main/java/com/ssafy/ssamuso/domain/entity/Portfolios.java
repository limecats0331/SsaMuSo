package com.ssafy.ssamuso.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Portfolios {

    @Id
    @GeneratedValue
    @Column(name = "portfolios_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(length = 300)
    private String link;
}
