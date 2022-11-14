package com.ssafy.ssamuso.util;

import com.ssafy.ssamuso.domain.entity.Board;
import com.ssafy.ssamuso.domain.entity.File;
import com.ssafy.ssamuso.domain.entity.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class EntityMaker {

    public static Board makeBoard(long id, User user){
        return Board.builder()
                .id(id)
                .title("title")
                .content("content")
                .uploadDate(LocalDate.parse("2022-09-11", DateTimeFormatter.ISO_DATE))
                .user(user)
                .name(user.getName())
                .deadline(LocalDate.parse("2022-12-11", DateTimeFormatter.ISO_DATE))
                .state(0)
                .beMax(2)
                .feMax(2)
                .appMax(2)
                .embMax(0)
                .beNow(1)
                .feNow(2)
                .appNow(0)
                .embNow(0)
                .build();
    }


    public static Optional<User> makeUser() {
        return Optional.of(User.builder()
                .id(10L)
                .username("userA")
                .classNum(4)
                .profileImg("src")
                .password("pass")
                .track("mobile")
                .term(8)
                .area("gumi")
                .build()
        );
    }

    public static File makeFile(long id){
        return File.builder()
                .id(id)
                .board(makeBoard(1L,makeUser().get()))
                .originalName("origin.png")
                .changedName("changedname")
                .url("url")
                .build();
    }
}
