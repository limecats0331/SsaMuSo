package com.ssafy.ssamuso.users.repository;

import com.ssafy.ssamuso.users.domain.Portfolios;
import com.ssafy.ssamuso.users.domain.User;
import com.ssafy.ssamuso.users.dto.UserMyPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class UserDTORepositoryTest {

    @Autowired
    UserDTORepository userDTORepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PortfoliosRepository portfoliosRepository;

    @Test
    @DisplayName("정보를 가져온다.")
    void getMyPage() {
        User user = makeUser();
        User savedUser = userRepository.save(user);
        Portfolios portfolios = makePortfolios(savedUser);
        Portfolios savedPortfolios = portfoliosRepository.save(portfolios);

        UserMyPage userMyPage = UserMyPage.createUserMyPage(savedUser, savedPortfolios);

        System.out.println("userMyPage = " + userMyPage);

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