package com.ssafy.ssamuso.service;

import com.ssafy.ssamuso.domain.dto.BoardDto;
import com.ssafy.ssamuso.domain.entity.Board;
import com.ssafy.ssamuso.domain.entity.BoardDelete;
import com.ssafy.ssamuso.domain.entity.enumtype.TechName;
import com.ssafy.ssamuso.repository.BoardDeleteRepository;
import com.ssafy.ssamuso.repository.BoardRepository;
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
    public Page<BoardDto> getListByTags(ArrayList<TechName> techNames, Pageable pageable) {
        return null;
    }

    @Override
    public BoardDto getBoardDto(Long id) {
        Optional<Board> boardOptional = boardRepository.findById(id);
        System.out.println(boardOptional.get());
        return new BoardDto(boardOptional.get());
    }

    @Override
    public Board getBoard(Long id) {
        Optional<Board> boardOptional = boardRepository.findById(id);

        return boardOptional.get();
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
