package com.ssafy.ssamuso.board;

import com.ssafy.ssamuso.board.boardDto.BoardDto;
import com.ssafy.ssamuso.board.users.TempUserService;
import com.ssafy.ssamuso.domain.entity.Board;
import com.ssafy.ssamuso.domain.entity.User;
import com.ssafy.ssamuso.domain.entity.enumtype.TechName;
import com.ssafy.ssamuso.file.FileService;
import com.ssafy.ssamuso.s3.S3Service;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Array;
import java.util.*;

@Controller
@RequestMapping("/boards")
@AllArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final TempUserService tempUserService;
    private final FileService fileService;

    @GetMapping
    public ResponseEntity<?> getList(Pageable pageable) throws Exception {

        Page<BoardDto> boardList = boardService.getList(pageable);
        return new ResponseEntity<>(boardList, HttpStatus.OK);

    }

    //입력에 List<MultipartFile>를 가지는 DTO 구현한 다음 받아서 처리할 것
    @PostMapping
    public ResponseEntity<?> wirteBoard(BoardDto boardDto, @AuthenticationPrincipal UserDetails userDetails,
                                        @RequestParam("images") List<MultipartFile> multipartFiles) throws Exception {
        String username = userDetails.getUsername();
        Optional<User> user = tempUserService.findByUsername(username);
        Board board = new Board(boardDto);
        board.setUser(user.get());
        board = boardService.insert(board);

        for (MultipartFile multipartFile : multipartFiles) {
            fileService.fileUpload(board.getId(), multipartFile);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("msg", "OK");
        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBoard(@PathVariable Long id) throws Exception {

        BoardDto boardDto = new BoardDto(boardService.getBoard(id));
        return new ResponseEntity<>(boardDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAll(@PathVariable Long id,@RequestBody BoardDto boardDto,
                                       @AuthenticationPrincipal UserDetails userDetails) throws Exception {
        Board tempBoard = boardService.getBoard(id);
        String username = userDetails.getUsername();
        Optional<User> user = tempUserService.findByUsername(username);

        if (tempBoard.getUser().getId()!=user.get().getId()) {
            throw new Exception("not your board");
        }
        tempBoard=Board.revise(tempBoard,boardDto);
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
        Optional<User> user = tempUserService.findByUsername(username);
        Board tempBoard = boardService.getBoard(id);

        if (tempBoard.getUser().getId()!=user.get().getId()) {
            throw new Exception("not your board");
        }

        boardService.deleteBoard(id);
        Map<String, Object> result = new HashMap<>();
        result.put("msg", "OK");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
