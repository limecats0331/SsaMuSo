package com.ssafy.ssamuso.domain.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Note {

    @Id
    @GeneratedValue
    @Column(name = "note_id")
    private Long id;

    @Column(nullable = false)
    private Long sender;

    @Column(nullable = false)
    private Long receiver;

    @Column(length = 500)
    private String content;

    @CreatedDate
    private LocalDateTime sendDate;

    private LocalDateTime readDate;

    private Integer state;
}
