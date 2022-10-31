package com.ssafy.ssamuso.users.repository;

import com.ssafy.ssamuso.users.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    //"kim", "gumi", 8, "mobile", 4, "src", "password"
    @Test
    @Rollback
    void save() {
        User user = User.builder()
                .username("kim")
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