package com.ssafy.ssamuso;

import com.ssafy.ssamuso.domain.entity.Portfolios;
import com.ssafy.ssamuso.domain.entity.User;

import java.util.Optional;

public class TestUtil {
    static public Optional<Portfolios> makePortfolios(User user) {
        return Optional.of(Portfolios.builder()
                .user(user)
                .link("link")
                .build());
    }

    static public Optional<User> makeUser() {
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
}
