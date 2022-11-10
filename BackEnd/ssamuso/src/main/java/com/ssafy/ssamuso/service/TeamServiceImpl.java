package com.ssafy.ssamuso.service;

import com.ssafy.ssamuso.domain.entity.Board;
import com.ssafy.ssamuso.domain.entity.Teammate;
import com.ssafy.ssamuso.domain.entity.User;
import com.ssafy.ssamuso.repository.BoardRepository;
import com.ssafy.ssamuso.repository.TeammateRepository;
import com.ssafy.ssamuso.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final TeammateRepository teammateRepository;

    @Override
    public List<Teammate> findTeammateByUsername(String username) throws IllegalArgumentException {
        Optional<User> user = userRepository.findByUsername(username);

        user.orElseThrow(() -> new IllegalArgumentException("No user"));

        return teammateRepository.findAllByUser(user.get());
    }

    @Override
    public List<Teammate> findTeammateByBoardId(Long boardId) throws IllegalArgumentException {
        Optional<Board> board = boardRepository.findById(boardId);

        board.orElseThrow(() -> new IllegalArgumentException("No Board"));

        return teammateRepository.findAllByBoard(board.get());
    }
}
