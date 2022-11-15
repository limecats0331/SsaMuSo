package com.ssafy.ssamuso.service;

import com.ssafy.ssamuso.domain.dto.BoardDetailDto;
import com.ssafy.ssamuso.domain.dto.BoardDto;
import com.ssafy.ssamuso.domain.entity.Board;
import com.ssafy.ssamuso.domain.entity.BoardDelete;
import com.ssafy.ssamuso.domain.entity.enumtype.TechName;
import com.ssafy.ssamuso.repository.BoardDeleteRepository;
import com.ssafy.ssamuso.repository.BoardRepository;
import lombok.AllArgsConstructor;
import org.aspectj.weaver.NewConstructorTypeMunger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final BoardDeleteRepository boardDeleteRepository;
    private final BoardTechstackService boardTechstackService;
    private final FileService fileService;


    @Override
    public Page<BoardDto> getList(Pageable pageable) {
        Page<Board> boards = boardRepository.findAll(pageable);
        List<List<String>> imgLists = new ArrayList<>();
        List<List<TechName>> techNameLists = new ArrayList<>();
        List<String> profileImgs = new ArrayList<>();
        Iterator iterator = boards.iterator();
        while (iterator.hasNext()) {
            Board board = (Board) iterator.next();
            List<String> imgs = fileService.findUrlByBoardId(board.getId());
            List<TechName> techNames = boardTechstackService.findByBoard(board);

            imgLists.add(imgs);
            techNameLists.add(techNames);
            profileImgs.add(board.getUser().getProfileImg());

        }

        Page<BoardDto> boardDtos = Board.convert(boardRepository.findAll(pageable), techNameLists, imgLists, profileImgs);
        return boardDtos;


    }

    @Override
    public Page<BoardDto> getListByTags(ArrayList<TechName> techNames, Pageable pageable) {
        return null;
    }

    @Override
    public BoardDto getBoardDto(Long id) {
        Optional<Board> boardOptional = boardRepository.findById(id);
        BoardDto boardDto = new BoardDto(boardOptional.get());
        boardDto.setTechNames(boardTechstackService.findByBoard(boardOptional.get()));
        boardDto.setProfileImg(boardOptional.get().getUser().getProfileImg());

        return boardDto;
    }

    @Override
    public BoardDetailDto getBoardDetailDto(Long id) {
        Optional<Board> boardOptional = boardRepository.findById(id);
        BoardDetailDto boardDetailDto = new BoardDetailDto(boardOptional.get());
        boardDetailDto.setTechNames(boardTechstackService.findByBoard(boardOptional.get()));
        boardDetailDto.setProfileImg(boardOptional.get().getUser().getProfileImg());

        return boardDetailDto;
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
