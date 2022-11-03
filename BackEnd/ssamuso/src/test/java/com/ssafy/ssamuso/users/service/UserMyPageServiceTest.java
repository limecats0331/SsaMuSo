package com.ssafy.ssamuso.users.service;

import com.ssafy.ssamuso.users.domain.Portfolios;
import com.ssafy.ssamuso.users.domain.User;
import com.ssafy.ssamuso.users.dto.UserMyPage;
import com.ssafy.ssamuso.users.repository.PortfoliosRepository;
import com.ssafy.ssamuso.users.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserMyPageServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PortfoliosRepository portfoliosRepository;
    UserMyPageService userMyPageService;

    @BeforeEach
    void beforeEach() {
        MockitoAnnotations.openMocks(this);

        Optional<User> user = makeUser();
        Mockito.doReturn(user).when(userRepository).findByUsername("userA");
        Mockito.doReturn(makePortfolios(user.get())).when(portfoliosRepository).findByUser(user.get());

        userMyPageService = new UserMyPageService(userRepository, portfoliosRepository);
    }

    @Test
    void getMyPage() throws Exception {
        //When
        UserMyPage myPageInfo = userMyPageService.findMyPageInfo("userA");

        //Then
        assertThat(myPageInfo.getUsername()).isEqualTo("userA");
        assertThat(myPageInfo.getPortfolios()).isEqualTo("link");
    }

    private static Optional<Portfolios> makePortfolios(User user) {
        return Optional.of(Portfolios.builder()
                .user(user)
                .link("link")
                .build());
    }

    private static Optional<User> makeUser() {
        return Optional.of(User.builder()
                .id(10L)
                .username("userA")
                .classNum(4)
                .profileImg("src")
                .password("pass")
                .track("mobile")
                .term(8)
                .area("gumi")
                .build()
        );
    }
}