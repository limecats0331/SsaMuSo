package com.ssafy.ssamuso.controller;

import com.ssafy.ssamuso.domain.dto.MyTeamDTO;
import com.ssafy.ssamuso.domain.dto.TeammateInfoDTO;
import com.ssafy.ssamuso.domain.dto.UserMyPageDTO;
import com.ssafy.ssamuso.domain.entity.Teammate;
import com.ssafy.ssamuso.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserMyPageService userMyPageService;
    private final UserService userService;

    private final BoardServiceImpl boardService;
    private final TeamServiceImpl teamService;

    @GetMapping
    public UserMyPageDTO userMyPage(@AuthenticationPrincipal UserDetails userDetails) {
        log.info("user my page in");
        String username = userDetails.getUsername();
        UserMyPageDTO myPageInfo = userMyPageService.findMyPageInfo(username);
        return myPageInfo;
    }

    @GetMapping("/{username}")
    public TeammateInfoDTO simpleInfo(@PathVariable String username) throws IllegalArgumentException {
        log.info("simple info in");
        Optional<TeammateInfoDTO> teammateByUsername = userService.findTeammateByUsername(username);

        teammateByUsername
                .orElseThrow(() -> new IllegalArgumentException("없는 유저"));

        return teammateByUsername.get();
    }

    @GetMapping("{username}/teams")
    public List<MyTeamDTO> getMyTeam(@PathVariable String username) {
        List<Teammate> myTeams = teamService.findTeammateByUsername(username);

        List<MyTeamDTO> result = new ArrayList<>();
        for (Teammate myTeam : myTeams) {
            result.add(MyTeamDTO.builder()
                    .username(username)
                    .boardId(myTeam.getBoard().getId())
                    .boardTitle(myTeam.getBoard().getTitle())
                    .state(myTeam.getState())
                    .role(myTeam.getRole())
                    .build());
        }

        return result;
    }

    @DeleteMapping
    public String deleteUser(@AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        userService.deleteUser(username);
        return "ok";
    }
}
