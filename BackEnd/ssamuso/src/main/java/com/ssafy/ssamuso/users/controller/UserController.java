package com.ssafy.ssamuso.users.controller;

import com.ssafy.ssamuso.users.dto.UserMyPage;
import com.ssafy.ssamuso.users.service.UserMyPageService;
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
    public UserMyPage userMyPage(@AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        UserMyPage myPageInfo = userMyPageService.findMyPageInfo(username);
        return myPageInfo;
    }
}
