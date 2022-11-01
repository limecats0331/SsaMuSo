package com.ssafy.ssamuso.board;

import com.ssafy.ssamuso.domain.entity.Board;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.*;

@Controller
@RequestMapping("/boards")
@AllArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public ResponseEntity<?> getList(Pageable pageable) {
        Page<Board> boardList = boardService.getList(pageable);

        return new ResponseEntity<>(boardList, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<?> wirteBoard(Board board) {
        Board temp = boardService.insert(board);

        return new ResponseEntity<>(temp, HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBoard(@PathVariable Long id) {
        Optional<Board> boardOptional = boardService.getBoard(id);

        return new ResponseEntity<>(boardOptional.get(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAll(@PathVariable Long id, Board board) {
        Optional<Board> boardOptional = boardService.getBoard(id);

        board.setId(id);
        boardService.insert(board);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updatePart(@PathVariable Long id, Map<String, String> map) {
        Optional<Board> board = boardService.getBoard(id);


        //querydsl 사용하여 동적인 쿼리로 패치 받기
        for (String string :map.keySet()) {

        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBoard(@PathVariable Long id){
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
