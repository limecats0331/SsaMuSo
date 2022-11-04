package com.ssafy.ssamuso.users.repository;

import com.ssafy.ssamuso.domain.entity.enumtype.Role;
import com.ssafy.ssamuso.domain.entity.Portfolios;
import com.ssafy.ssamuso.domain.entity.User;
import com.ssafy.ssamuso.repository.PortfoliosRepository;
import com.ssafy.ssamuso.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PortfoliosRepositoryTest {

    @Autowired
    PortfoliosRepository portfoliosRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    void save() {
        User user = createUser();
        userRepository.save(user);
        Portfolios portfolios = makePortfolios(user);
        Portfolios saved = portfoliosRepository.save(portfolios);

        System.out.println("portfolios = " + portfolios);
        System.out.println("saved = " + saved);

        assertThat(saved.getUser()).isEqualTo(user);
        assertThat(saved.getLink()).isEqualTo(portfolios.getLink());
    }

    @Test
    void findByUser() throws Exception {
        //Given
        User user = createUser();
        userRepository.save(user);
        Portfolios portfolios = makePortfolios(user);
        Portfolios saved = portfoliosRepository.save(portfolios);

        //When
        Portfolios foundPortfolios = portfoliosRepository.findByUser(user).get();

        //Then
        assertThat(foundPortfolios).isEqualTo(saved);
    }

    private static User createUser() {
        return User.builder()
                .username("userA")
                .area("gumi")
                .term(8)
                .track("mobile")
                .classNum(4)
                .profileImg("src")
                .password("password")
                .email("test@email.com")
                .role(Role.USER)
                .name("test")
                .build();
    }

    private static Portfolios makePortfolios(User user) {
        return Portfolios.builder()
                .id(1L)
                .user(user)
                .link("link")
                .build();
    }


}