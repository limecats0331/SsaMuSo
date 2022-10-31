package com.ssafy.ssamuso.users.repository;

import com.ssafy.ssamuso.users.domain.Portfolios;
import com.ssafy.ssamuso.users.domain.User;
import com.ssafy.ssamuso.users.dto.UserMyPage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class UserDTORepository {
    private final EntityManager em;

    public UserMyPage myPage(Long userId) {
        User user = em.find(User.class, userId);
        Portfolios portfolios = em.find(Portfolios.class, userId);
        UserMyPage userMyPage = UserMyPage.createUserMyPage(user, portfolios);
        return userMyPage;
    }
}
