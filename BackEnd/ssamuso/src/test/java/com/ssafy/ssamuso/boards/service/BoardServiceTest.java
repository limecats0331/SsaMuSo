package com.ssafy.ssamuso.boards.service;

import com.ssafy.ssamuso.domain.dto.BoardDto;
import com.ssafy.ssamuso.domain.entity.Board;
import com.ssafy.ssamuso.domain.entity.User;
import com.ssafy.ssamuso.repository.BoardDeleteRepository;
import com.ssafy.ssamuso.repository.BoardRepository;
import com.ssafy.ssamuso.service.BoardService;
import com.ssafy.ssamuso.service.BoardServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        User user = makeUser().get();
        Board board = makeBoard(1,user);
        BoardDto boardDto = new BoardDto(board);
        Optional<Board> boardOptional= Optional.of(board);
        doReturn(boardOptional).when(boardRepository).findById(anyLong());
        BoardDto result = boardService.getBoardDto(1L);
        assertThat(boardDto).isEqualTo(result);

    }



    private static Board makeBoard(long id, User user){
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


    private static Optional<User> makeUser() {
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
