package com.ssafy.ssamuso;

import com.ssafy.ssamuso.domain.dto.TeammateInfoDTO;
import com.ssafy.ssamuso.domain.entity.*;
import com.ssafy.ssamuso.domain.entity.enumtype.TeamRole;
import com.ssafy.ssamuso.domain.entity.enumtype.TechName;

import java.time.LocalDate;
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

    static public Optional<TeammateInfoDTO> makeTeammateInfoDTO() {
        TeammateInfoDTO teammateInfoDTO = new TeammateInfoDTO();
        teammateInfoDTO.setUsername("userA");
        teammateInfoDTO.setPortfoliosLink("link");
        teammateInfoDTO.setTechstacks(List.of(TechName.Spring, TechName.React));

        return Optional.of(teammateInfoDTO);
    }

    static public Optional<Teammate> makeTeammate(Board board, User user, TeamRole teamRole) {
        Teammate teammate = new Teammate();
        teammate.setId(1L);
        teammate.setBoard(board);
        teammate.setUser(user);
        teammate.setState(0);
        teammate.setRole(teamRole);
        return Optional.of(teammate);
    }

    static public Optional<Board> makeBoard(User user) {
        return Optional.of(Board.builder()
                .id(1L)
                .title("title")
                .content("content")
                .name(user.getUsername())
                .state(0)
                .beMax(3)
                .beNow(2)
                .feMax(3)
                .feNow(1)
                .embMax(3)
                .embNow(0)
                .appMax(3)
                .appNow(3)
                .uploadDate(LocalDate.now())
                .deadline(LocalDate.now().plusMonths(1))
                .user(user)
                .build());
    }

}
