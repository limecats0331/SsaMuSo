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

    public UserMyPage findMyPageInfo(String username) throws IllegalArgumentException {
        if (!userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("사용자가 없습니다.");
        }
        User user = userRepository.findByUsername(username).get();
        if (!portfoliosRepository.findByUser(user).isPresent()) {
            throw new IllegalArgumentException("포트폴리오가 없습니다.");
        }
        Portfolios portfolios = portfoliosRepository.findByUser(user).get();
        UserMyPage userMyPage = UserMyPage.createUserMyPage(user, portfolios);
        return userMyPage;
    }
}
