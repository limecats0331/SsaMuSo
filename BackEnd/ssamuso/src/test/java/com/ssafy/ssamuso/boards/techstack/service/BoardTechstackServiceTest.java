package com.ssafy.ssamuso.boards.techstack.service;

import com.ssafy.ssamuso.TestUtil;
import com.ssafy.ssamuso.domain.entity.Board;
import com.ssafy.ssamuso.domain.entity.BoardTechstack;
import com.ssafy.ssamuso.domain.entity.Techstack;
import com.ssafy.ssamuso.domain.entity.User;
import com.ssafy.ssamuso.domain.entity.enumtype.TechName;
import com.ssafy.ssamuso.repository.BoardTechstackRepository;
import com.ssafy.ssamuso.repository.TechstackRepository;
import com.ssafy.ssamuso.service.BoardTechstackService;
import com.ssafy.ssamuso.service.BoardTechstackServiceImpl;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class BoardTechstackServiceTest {

    @Mock
    BoardTechstackRepository boardTechstackRepository;
    @Mock
    TechstackRepository techstackRepository;


    @Test
    void findByBoardTest() {

        User user = TestUtil.makeUser().get();
        Board board = TestUtil.makeBoard(1L, user);
        Techstack techstack1 = TestUtil.makeTechstack(1L, TechName.Spring);
        Techstack techstack2 = TestUtil.makeTechstack(2L, TechName.JPA);

        List<BoardTechstack> boardTechstackList = new ArrayList<>();
        boardTechstackList.add(TestUtil.makeBoardTechstack(1L, board, techstack1));
        boardTechstackList.add(TestUtil.makeBoardTechstack(2L, board, techstack2));

        doReturn(boardTechstackList).when(boardTechstackRepository).findByBoard(any(Board.class));
        System.out.println(boardTechstackList.get(0).getTechstack());
        System.out.println(boardTechstackList.get(1).getTechstack());

        BoardTechstackService boardTechstackService =
                new BoardTechstackServiceImpl(boardTechstackRepository, techstackRepository);


        List<TechName> result = boardTechstackService.findByBoard(board);
        System.out.println(result.get(0));

        assertThat(TechName.Spring).isEqualTo(result.get(0));
        assertThat(TechName.JPA).isEqualTo(result.get(1));

    }

    @Test
    void saveTest() {

        User user = TestUtil.makeUser().get();
        Board board = TestUtil.makeBoard(1L, user);
        Techstack techstack1 = TestUtil.makeTechstack(1L, TechName.Spring);
        Techstack techstack2 = TestUtil.makeTechstack(2L, TechName.JPA);



        List<TechName> techNames = new ArrayList<>();
        techNames.add(TechName.Spring);
        techNames.add(TechName.JPA);

        BoardTechstack boardTechstack = TestUtil.makeBoardTechstack(null, board, techstack1);
        BoardTechstack boardTechstack2 = TestUtil.makeBoardTechstack(null, board, techstack2);
        BoardTechstack mockReturn1 = TestUtil.makeBoardTechstack(1L, board, techstack1);
        BoardTechstack mockReturn2 = TestUtil.makeBoardTechstack(2L, board, techstack2);

        doReturn(Optional.of(techstack1)).when(techstackRepository).findByTechName(TechName.Spring);
        doReturn(Optional.of(techstack2)).when(techstackRepository).findByTechName(TechName.JPA);
        given(boardTechstackRepository.save(boardTechstack)).willReturn(mockReturn1);
        given(boardTechstackRepository.save(boardTechstack2)).willReturn(mockReturn2);
//        doReturn(mockReturn1).when(boardTechstackRepository).save(boardTechstack);
//        doReturn(mockReturn2).when(boardTechstackRepository).save(boardTechstack2);

        BoardTechstackService boardTechstackService =
                new BoardTechstackServiceImpl(boardTechstackRepository, techstackRepository);

        List<Techstack> boardTechstacks = boardTechstackService.save(board, techNames);


        assertThat(TechName.Spring).isEqualTo(boardTechstacks.get(0).getTechName());
        assertThat(TechName.JPA).isEqualTo(boardTechstacks.get(1).getTechName());


    }
}