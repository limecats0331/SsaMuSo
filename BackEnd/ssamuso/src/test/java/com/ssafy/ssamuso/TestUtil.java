package com.ssafy.ssamuso;

import com.ssafy.ssamuso.domain.dto.TeammateInfoDTO;
import com.ssafy.ssamuso.domain.entity.*;
import com.ssafy.ssamuso.domain.entity.enumtype.Role;
import com.ssafy.ssamuso.domain.entity.enumtype.TeamRole;
import com.ssafy.ssamuso.domain.entity.enumtype.TechName;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class TestUtil {


    public static BoardTechstack makeBoardTechstack(Long id, Board board,Techstack techstack){
        return BoardTechstack.builder()
                .id(id)
                .board(board)
                .techstack(techstack)
                .build();


    }

    public static Techstack makeTechstack(long id, TechName techName){
        return Techstack.builder()
                .id(id)
                .techName(techName)
                .build();
    }

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




    public static File makeFile(long id){
        return File.builder()
                .id(id)
                .board(makeBoard(1L,makeUser().get()))
                .originalName("origin.png")
                .changedName("changedname")
                .url("url")
                .build();
    }
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
                .name("Mr.kim")
                .classNum(4)
                .profileImg("src")
                .password("pass")
                .track("mobile")
                .term(8)
                .area("gumi")
                .email("email test")
                .role(Role.USER)
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
        teammate.setBoard(board);
        teammate.setUser(user);
        teammate.setState(0);
        teammate.setRole(teamRole);
        return Optional.of(teammate);
    }

    static public Optional<Board> makeBoard(User user, Long id, String title) {
        return Optional.of(Board.builder()
                .id(id)
                .title(title)
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
