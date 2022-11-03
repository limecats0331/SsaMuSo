package com.ssafy.ssamuso.board;

import com.ssafy.ssamuso.domain.entity.Board;
import com.ssafy.ssamuso.domain.entity.enumtype.TechName;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
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

        try{
            Page<Board> boardList = boardService.getList(pageable);
            return new ResponseEntity<>(boardList, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @PostMapping
    public ResponseEntity<?> wirteBoard(Board board, Authentication authentication) {
        UserDetails userDetails = (UserDetails)authentication.getDetails();
        try {
            Board temp = boardService.insert(board);
            if(temp.getUser().equals(userDetails));
            return new ResponseEntity<>(HttpStatus.MULTIPLE_CHOICES);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBoard(@PathVariable Long id) {

        try{
            Optional<Board> boardOptional = boardService.getBoard(id);
            return new ResponseEntity<>(boardOptional.get(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAll(@PathVariable Long id, Board board,Authentication authentication) {

        UserDetails userDetails = (UserDetails)authentication.getDetails();
        try {
            Optional<Board> boardOptional = boardService.getBoard(id);
            board.setId(id);
            boardService.insert(board);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

//    @PatchMapping("/{id}")
//    public ResponseEntity<?> updatePart(@PathVariable Long id, Map<String, String> map) {
//        Optional<Board> board = boardService.getBoard(id);
//
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBoard(@PathVariable Long id,Authentication authentication) {

        try {
            UserDetails userDetails = (UserDetails)authentication.getDetails();
            boardService.deleteBoard(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
