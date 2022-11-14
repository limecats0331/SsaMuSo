package com.ssafy.ssamuso.service;

import com.ssafy.ssamuso.domain.entity.Board;
import com.ssafy.ssamuso.domain.entity.BoardTechstack;
import com.ssafy.ssamuso.domain.entity.Techstack;
import com.ssafy.ssamuso.domain.entity.enumtype.TechName;
import com.ssafy.ssamuso.repository.BoardTechstackRepository;
import com.ssafy.ssamuso.repository.TechstackRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BoardTechstackServiceImpl implements BoardTechstackService {

    private final BoardTechstackRepository boardTechstackRepository;
    private final TechstackRepository techstackRepository;

    @Override
    public List<TechName> findByBoard(Board board) {
        List<BoardTechstack> boardTechstacks = boardTechstackRepository.findByBoard(board);

        ArrayList<TechName> result = new ArrayList<>();
        for (BoardTechstack boardTechstach : boardTechstacks) {
            result.add(boardTechstach.getTechstack().getTechName());
        }
        return result;
    }

    @Override
    @Transactional
    public List<Techstack> save(Board board, List<TechName> techNames) {
        List<Techstack> techstacks = new ArrayList<>();
        for (TechName techName : techNames) {
            Optional<Techstack> techstackOptional = techstackRepository.findByTechName(techName);
            BoardTechstack boardTechstack = BoardTechstack.builder()
                    .board(board).techstack(techstackOptional.get()).build();
            BoardTechstack saved = boardTechstackRepository.save(boardTechstack);
            techstacks.add(saved.getTechstack());
        }
        return techstacks;
    }
}
