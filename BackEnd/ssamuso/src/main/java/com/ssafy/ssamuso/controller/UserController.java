package com.ssafy.ssamuso.controller;

import com.ssafy.ssamuso.domain.dto.TeammateInfoDTO;
import com.ssafy.ssamuso.domain.dto.UserMyPageDTO;
import com.ssafy.ssamuso.service.UserMyPageService;
import com.ssafy.ssamuso.service.UserServiceImlp;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserMyPageService userMyPageService;
    private final UserServiceImlp userServiceImlp;

    @GetMapping
    public UserMyPageDTO userMyPage(@AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        UserMyPageDTO myPageInfo = userMyPageService.findMyPageInfo(username);
        return myPageInfo;
    }

    @GetMapping("/{username}")
    public TeammateInfoDTO simpleInfo(@PathVariable String username) throws IllegalArgumentException {
        Optional<TeammateInfoDTO> teammateByUsername = userServiceImlp.findTeammateByUsername(username);

        teammateByUsername
                .orElseThrow(()->new IllegalArgumentException("없는 유저"));

        return teammateByUsername.get();
    }
}
