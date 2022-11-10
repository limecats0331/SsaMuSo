package com.ssafy.ssamuso.repository;

import com.ssafy.ssamuso.TestUtil;
import com.ssafy.ssamuso.domain.entity.Board;
import com.ssafy.ssamuso.domain.entity.Teammate;
import com.ssafy.ssamuso.domain.entity.User;
import com.ssafy.ssamuso.domain.entity.enumtype.TeamRole;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TeammateRepositoryTest {
    @Autowired
    TeammateRepository teammateRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BoardRepository boardRepository;

    @Test
    @DisplayName("팀메이트 목록 검색")
    void findTeammateList() throws Exception {
        //Given
        Optional<User> user = TestUtil.makeUser();
        user.get().setId(null);
        userRepository.save(user.get());

        Optional<Board> board1 = TestUtil.makeBoard(user.get(), 1L, "title");
        board1.get().setId(null);
        Optional<Board> board2 = TestUtil.makeBoard(user.get(), 2L, "title2");
        board2.get().setId(null);
        boardRepository.save(board1.get());
        boardRepository.save(board2.get());

        Optional<Teammate> teammate1 = TestUtil.makeTeammate(board1.get(), user.get(), TeamRole.BackEnd);
        Optional<Teammate> teammate2 = TestUtil.makeTeammate(board2.get(), user.get(), TeamRole.FrontEnd);
        teammateRepository.save(teammate1.get());
        teammateRepository.save(teammate2.get());

        //When
        List<Teammate> findResult = teammateRepository.findAllByUser(user.get());

        //Then
        for (Teammate teammate : findResult) {
            assertThat(teammate).isIn(List.of(teammate1.get(),teammate2.get()));
        }
    }
}