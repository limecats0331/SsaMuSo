package com.ssafy.ssamuso.domain.entity;

import com.ssafy.ssamuso.domain.entity.enumtype.TechName;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Techstack {

    @Id
    @GeneratedValue
    @Column(name = "techstack_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TechName techName;
}
