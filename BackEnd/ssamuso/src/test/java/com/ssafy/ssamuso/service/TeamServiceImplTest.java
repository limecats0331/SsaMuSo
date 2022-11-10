package com.ssafy.ssamuso.service;

import com.ssafy.ssamuso.TestUtil;
import com.ssafy.ssamuso.domain.entity.Board;
import com.ssafy.ssamuso.domain.entity.Teammate;
import com.ssafy.ssamuso.domain.entity.User;
import com.ssafy.ssamuso.domain.entity.enumtype.TeamRole;
import com.ssafy.ssamuso.repository.BoardRepository;
import com.ssafy.ssamuso.repository.TeammateRepository;
import com.ssafy.ssamuso.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TeamServiceImplTest {

    @Mock
    TeammateRepository teammateRepository;
    @Mock
    UserRepository userRepository;

    @Mock
    BoardRepository boardRepository;


    @Test
    @DisplayName("유저 이름으로 검색")
    void findByUsername() throws Exception {
        //Given
        Optional<User> user = TestUtil.makeUser();
        doReturn(user).when(userRepository).findByUsername("userA");

        Optional<Board> board = TestUtil.makeBoard(user.get(), 1L, "test");

        Optional<Teammate> teammate = TestUtil.makeTeammate(board.get(), user.get(), TeamRole.BackEnd);
        doReturn(List.of(teammate.get())).when(teammateRepository).findAllByUser(user.get());

        //When
        TeamServiceImpl teamService = new TeamServiceImpl(userRepository, boardRepository, teammateRepository);
        List<Teammate> result = teamService.findTeamByUsername("userA");

        //Then
        assertThat(result.size()).isEqualTo(1);
        assertThat(result).isEqualTo(List.of(teammate.get()));
    }

}