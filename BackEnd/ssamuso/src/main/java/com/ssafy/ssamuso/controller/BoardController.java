package com.ssafy.ssamuso.controller;

import com.ssafy.ssamuso.service.BoardService;
import com.ssafy.ssamuso.domain.dto.BoardDto;
import com.ssafy.ssamuso.domain.entity.Board;
import com.ssafy.ssamuso.domain.entity.User;
import com.ssafy.ssamuso.service.FileService;
import com.ssafy.ssamuso.service.UserServiceImlp;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Controller
@RequestMapping("/boards")
@AllArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final UserServiceImlp userServiceImlp;
    private final FileService fileService;

    @GetMapping
    public ResponseEntity<?> getList(Pageable pageable) throws Exception {

        Page<BoardDto> boardList = boardService.getList(pageable);
        return new ResponseEntity<>(boardList, HttpStatus.OK);

    }


    @PostMapping
    public ResponseEntity<?> wirteBoard(@RequestParam Map<String, Object> map
                                        ,@RequestParam("images") ArrayList<MultipartFile> multipartFiles
                                        ,@AuthenticationPrincipal UserDetails userDetails) throws Exception {
        String username = userDetails.getUsername();
        Optional<User> user = userServiceImlp.findByUsername(username);
        Board board = new Board(map);
        board.setUser(user.get());
//        board.setUser(User.builder().id(1L).build());
        board = boardService.insert(board);

        for (MultipartFile multipartFile : multipartFiles) {
            System.out.println("file test");
            fileService.fileUpload(board.getId(), multipartFile);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("msg", "OK");
        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBoard(@PathVariable Long id) throws Exception {
        Map<String, Object> result = new HashMap<>();
        result.put("images", fileService.findUrlByBoardId(id));
        result.put("boards", boardService.getBoardDto(id));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAll(@PathVariable Long id, @RequestBody  BoardDto boardDto
                                       , @AuthenticationPrincipal UserDetails userDetails) throws Exception {
//        System.out.println(boardDto);
        Board tempBoard = boardService.getBoard(id);
        String username = userDetails.getUsername();
        Optional<User> user = userServiceImlp.findByUsername(username);

//        Optional<User> user = userServiceImlp.findByUsername("test");
        if (tempBoard.getUser().getId() != user.get().getId()) {
            throw new Exception("not your board");
        }
        tempBoard = Board.revise(tempBoard,boardDto);
        boardService.insert(tempBoard);
        Map<String, Object> result = new HashMap<>();
        result.put("msg", "OK");
        return new ResponseEntity<>(result, HttpStatus.OK);

    }

//    @PatchMapping("/{id}")
//    public ResponseEntity<?> updatePart(@PathVariable Long id, Map<String, String> map) {
//        Optional<Board> board = boardService.getBoard(id);
//
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBoard(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) throws Exception {
        String username = userDetails.getUsername();
        Optional<User> user = userServiceImlp.findByUsername(username);
//        Optional<User> user = userServiceImlp.findByUsername("test");
        Board tempBoard = boardService.getBoard(id);
        fileService.delete(id);

//        System.out.println(tempBoard.getUser().getId());
//        System.out.println(user.get().getId());
        if (tempBoard.getName().equals(user.get().getUsername()) ) {
            throw new Exception("not your board");
        }

        boardService.deleteBoard(id);
        Map<String, Object> result = new HashMap<>();
        result.put("msg", "OK");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
