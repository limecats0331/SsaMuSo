package com.ssafy.ssamuso.controller;

import com.ssafy.ssamuso.domain.dto.UserMyPageDTO;
import com.ssafy.ssamuso.service.UserMyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserMyPageService userMyPageService;

    @GetMapping
    public UserMyPageDTO userMyPage(@AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        UserMyPageDTO myPageInfo = userMyPageService.findMyPageInfo(username);
        return myPageInfo;
    }
}
