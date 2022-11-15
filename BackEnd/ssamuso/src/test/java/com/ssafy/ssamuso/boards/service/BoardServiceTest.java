package com.ssafy.ssamuso.boards.service;

import com.ssafy.ssamuso.TestUtil;
import com.ssafy.ssamuso.domain.dto.BoardDto;
import com.ssafy.ssamuso.domain.entity.Board;
import com.ssafy.ssamuso.domain.entity.BoardTechstack;
import com.ssafy.ssamuso.domain.entity.Techstack;
import com.ssafy.ssamuso.domain.entity.User;
import com.ssafy.ssamuso.domain.entity.enumtype.TechName;
import com.ssafy.ssamuso.repository.BoardDeleteRepository;
import com.ssafy.ssamuso.repository.BoardRepository;
import com.ssafy.ssamuso.service.BoardService;
import com.ssafy.ssamuso.service.BoardServiceImpl;
import com.ssafy.ssamuso.service.BoardTechstackService;
import com.ssafy.ssamuso.util.EntityMaker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class BoardServiceTest {

    @Mock
    private BoardRepository boardRepository;

    @Mock
    private BoardDeleteRepository boardDeleteRepository;
    @Mock
    BoardTechstackService boardTechstackService;
    private BoardService boardService;



    @Test
    void getBoardDtoTest(){



        User user = EntityMaker.makeUser().get();
        Board board = EntityMaker.makeBoard(1,user);
        List<Techstack> techstacks = TestUtil.makeTechstacks();
        List<BoardTechstack> boardTechstacks = TestUtil.makeBoardTechstacks(board ,techstacks);
        board.setBoardTechstacks(boardTechstacks);
        Optional<Board> boardOptional= Optional.of(board);
        BoardDto boardDto = new BoardDto(board);
        List<TechName> techNames = TestUtil.makeTechNames();
        boardDto.setProfileImg(user.getProfileImg());
        boardDto.setTechNames(techNames);


        doReturn(boardOptional).when(boardRepository).findById(anyLong());
        doReturn(techNames).when(boardTechstackService).findByBoard(any(Board.class));

        boardService = new BoardServiceImpl(boardRepository,boardDeleteRepository, boardTechstackService);

        BoardDto result = boardService.getBoardDto(1L);
        result.setTechNames(techNames);

        assertThat(boardDto).isEqualTo(result);

    }




}
