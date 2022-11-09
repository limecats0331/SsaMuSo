package com.ssafy.ssamuso;

import com.ssafy.ssamuso.domain.entity.Portfolios;
import com.ssafy.ssamuso.domain.entity.Techstack;
import com.ssafy.ssamuso.domain.entity.User;
import com.ssafy.ssamuso.domain.entity.UserTechstack;
import com.ssafy.ssamuso.domain.entity.enumtype.TechName;

import java.util.ArrayList;
import java.util.List;
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

    static public Optional<List<UserTechstack>> makeUserTechStacks() {
        Techstack techstack1 = Techstack.builder()
                .id(1L)
                .techName(TechName.Spring)
                .build();
        Techstack techstack2 = Techstack.builder()
                .id(2L)
                .techName(TechName.React)
                .build();

        UserTechstack userTechstack1 = new UserTechstack();
        userTechstack1.setId(1L);
        userTechstack1.setUser(makeUser().get());
        userTechstack1.setTechstack(techstack1);

        UserTechstack userTechstack2 = new UserTechstack();
        userTechstack2.setId(2L);
        userTechstack2.setUser(makeUser().get());
        userTechstack2.setTechstack(techstack2);

        return Optional.of(List.of(userTechstack1, userTechstack2));
    }
}
