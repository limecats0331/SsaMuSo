package com.ssafy.ssamuso.board;

import com.ssafy.ssamuso.domain.entity.Board;
import com.ssafy.ssamuso.domain.entity.BoardDelete;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;
    private final BoardDeleteRepository boardDeleteRepository;


    @Override
    public Page<Board> getList(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    @Override
    public Optional<Board> getBoard(Long id) {
        return boardRepository.findById(id);
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
