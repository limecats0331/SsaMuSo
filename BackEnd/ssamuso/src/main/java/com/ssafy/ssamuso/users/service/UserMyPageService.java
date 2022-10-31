package com.ssafy.ssamuso.users.service;

import com.ssafy.ssamuso.users.domain.Portfolios;
import com.ssafy.ssamuso.users.domain.User;
import com.ssafy.ssamuso.users.dto.UserMyPage;
import com.ssafy.ssamuso.users.repository.PortfoliosRepository;
import com.ssafy.ssamuso.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMyPageService {
    private final UserRepository userRepository;
    private final PortfoliosRepository portfoliosRepository;

    public UserMyPage findMyPageInfo(Long id) {
        User user = userRepository.findById(id).get();
        Portfolios portfolios = portfoliosRepository.findById(id).get();
        UserMyPage userMyPage = UserMyPage.createUserMyPage(user, portfolios);
        return userMyPage;
    }
}
