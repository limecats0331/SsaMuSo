package com.ssafy.ssamuso.users.service;

import com.ssafy.ssamuso.users.domain.Portfolios;
import com.ssafy.ssamuso.users.domain.User;
import com.ssafy.ssamuso.users.dto.UserMyPage;
import com.ssafy.ssamuso.users.repository.PortfoliosRepository;
import com.ssafy.ssamuso.users.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

class UserMyPageServiceTest {
    @Mock
    UserRepository userRepository;
    @Mock
    PortfoliosRepository portfoliosRepository;
    UserMyPageService userMyPageService = new UserMyPageService(userRepository, portfoliosRepository);

    @Test
    @Transactional
    void getMyPage() throws Exception {
        Optional<User> user = makeUser();
        //Given
        Mockito.doReturn(makeUser()).when(userRepository).findByUsername("userA");
        Mockito.doReturn(Optional.of(makePortfolios(user.get()))).when(portfoliosRepository).findByUser(user.get());

        //When
        UserMyPage myPageInfo = userMyPageService.findMyPageInfo("userA");
        System.out.println("myPageInfo = " + myPageInfo);

        //Then
    }

    private static Portfolios makePortfolios(User user) {
        Portfolios portfolios = new Portfolios();
        portfolios.setUser(user);
        portfolios.setLink("link");
        return portfolios;
    }

    private static Optional<User> makeUser() {
        User user = new User();
        user.setUsername("userA");
        user.setPassword("pass");
        user.setTerm(8);
        user.setArea("gumi");
        user.setClassNum(4);
        user.setProfileImg("src");
        user.setTrack("mobile");
        return Optional.of(user);
    }
}