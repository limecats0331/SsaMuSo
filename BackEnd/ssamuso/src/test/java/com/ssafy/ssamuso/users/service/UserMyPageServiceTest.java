package com.ssafy.ssamuso.users.service;

import com.ssafy.ssamuso.users.domain.Portfolios;
import com.ssafy.ssamuso.users.domain.User;
import com.ssafy.ssamuso.users.dto.UserMyPage;
import com.ssafy.ssamuso.users.repository.PortfoliosRepository;
import com.ssafy.ssamuso.users.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserMyPageServiceTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PortfoliosRepository portfoliosRepository;
    @Autowired
    UserMyPageService userMyPageService;

    @Test
    void getMyPage() throws Exception {
        //Given
        User user = makeUser();
        Portfolios portfolios = makePortfolios(user);
        User savedUser = userRepository.save(user);
        Portfolios savedPortfolios = portfoliosRepository.save(portfolios);

        //When
        UserMyPage myPageInfo = userMyPageService.findMyPageInfo(savedUser.getId());
        UserMyPage userMyPage = UserMyPage.createUserMyPage(savedUser,    savedPortfolios);

        //Then
        assertThat(myPageInfo).isEqualTo(userMyPage);
    }

    private static Portfolios makePortfolios(User user) {
        Portfolios portfolios = new Portfolios();
        portfolios.setUser(user);
        portfolios.setLink("link");
        return portfolios;
    }

    private static User makeUser() {
        User user = new User();
        user.setUsername("userA");
        user.setPassword("pass");
        user.setTerm(8);
        user.setArea("gumi");
        user.setClassNum(4);
        user.setProfileImg("src");
        return user;
    }
}