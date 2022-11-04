package com.ssafy.ssamuso.board;

import com.ssafy.ssamuso.board.boardDto.BoardDto;
import com.ssafy.ssamuso.domain.entity.Board;
import com.ssafy.ssamuso.domain.entity.BoardDelete;
import com.ssafy.ssamuso.domain.entity.User;
import com.ssafy.ssamuso.domain.entity.enumtype.TechName;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;
    private final BoardDeleteRepository boardDeleteRepository;


    @Override
    public Page<BoardDto> getList(Pageable pageable) {

        return Board.convert(boardRepository.findAll(pageable));
    }

    @Override
    public Page<Board> getListByTags(ArrayList<TechName> techNames, Pageable pageable) {
        return null;
    }

    @Override
    public Board getBoard(Long id) {
        Board board = boardRepository.findById(id).get();
        User user = new User();
        user.setId(board.getUser().getId());
        board.setUser(user);

        return board;
    }

    @Override
    public Board insert(Board board) {
        return boardRepository.save(board);
    }

    @Override
    @Transactional
    public int deleteBoard(Long id) {

        Optional<Board> boardOptional = boardRepository.findById(id);
        boardDeleteRepository.save(new BoardDelete(boardOptional.get()));
        boardRepository.deleteById(id);
        return 1;
    }
}
