package com.ssafy.ssamuso.boards.service;

import com.ssafy.ssamuso.TestUtil;
import com.ssafy.ssamuso.domain.dto.BoardDto;
import com.ssafy.ssamuso.domain.entity.Board;
import com.ssafy.ssamuso.domain.entity.User;
import com.ssafy.ssamuso.repository.BoardDeleteRepository;
import com.ssafy.ssamuso.repository.BoardRepository;
import com.ssafy.ssamuso.service.BoardService;
import com.ssafy.ssamuso.service.BoardServiceImpl;
import com.ssafy.ssamuso.util.TestEntityMaker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class BoardServiceTest {

    @Mock
    private BoardRepository boardRepository;

    @Mock
    private BoardDeleteRepository boardDeleteRepository;


    private BoardService boardService;



    @Test
    void getBoardDtoTest(){
        boardService = new BoardServiceImpl(boardRepository,boardDeleteRepository);
        User user = TestUtil.makeUser().get();
        Board board = TestUtil.makeBoard(1,user);
        BoardDto boardDto = new BoardDto(board);
        Optional<Board> boardOptional= Optional.of(board);
        doReturn(boardOptional).when(boardRepository).findById(anyLong());
        BoardDto result = boardService.getBoardDto(1L);
        assertThat(boardDto).isEqualTo(result);

    }




}
