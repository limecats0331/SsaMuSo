package com.ssafy.ssamuso.service;

import com.ssafy.ssamuso.domain.entity.Portfolios;
import com.ssafy.ssamuso.domain.entity.User;
import com.ssafy.ssamuso.domain.dto.UserMyPageDTO;
import com.ssafy.ssamuso.repository.PortfoliosRepository;
import com.ssafy.ssamuso.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserMyPageService {
    private final UserRepository userRepository;
    private final PortfoliosRepository portfoliosRepository;

    public UserMyPageDTO findMyPageInfo(String username) throws IllegalArgumentException {
        if (!userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("사용자가 없습니다.");
        }
        User user = userRepository.findByUsername(username).get();
        if (!portfoliosRepository.findByUser(user).isPresent()) {
            throw new IllegalArgumentException("포트폴리오가 없습니다.");
        }
        Portfolios portfolios = portfoliosRepository.findByUser(user).get();
        UserMyPageDTO userMyPageDTO = UserMyPageDTO.createUserMyPage(user, portfolios);
        return userMyPageDTO;
    }
}
