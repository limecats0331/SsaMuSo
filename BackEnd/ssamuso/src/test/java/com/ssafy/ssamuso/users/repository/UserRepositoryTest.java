package com.ssafy.ssamuso.users.repository;

import com.ssafy.ssamuso.domain.entity.User;
import com.ssafy.ssamuso.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    //"kim", "gumi", 8, "mobile", 4, "src", "password"
    @Test
    void save() {
        User user = User.builder()
                .username("Test kim")
                .area("gumi")
                .term(8)
                .track("mobile")
                .classNum(4)
                .profileImg("src")
                .password("password")
                .build();

        User savedUser = userRepository.save(user);

        System.out.println("savedUser = " + savedUser);
        assertThat(user).isEqualTo(savedUser);
    }
}