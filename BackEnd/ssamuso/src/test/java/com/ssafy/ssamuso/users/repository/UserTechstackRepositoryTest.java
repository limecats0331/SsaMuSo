package com.ssafy.ssamuso.users.repository;

import com.ssafy.ssamuso.common.repository.TechstackRepository;
import com.ssafy.ssamuso.domain.entity.enumtype.TechName;
import com.ssafy.ssamuso.common.domain.Techstack;
import com.ssafy.ssamuso.users.domain.User;
import com.ssafy.ssamuso.users.domain.UserTechstack;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserTechstackRepositoryTest {


    @Autowired
    UserTechstackRepository userTechstackRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TechstackRepository techstackRepository;

    @Test
    void save() throws Exception {
        //Given
        User user = makeUser();
        Techstack spring = makeTechstack(TechName.Spring);
        User saveUser = userRepository.save(user);
        Techstack saveTechstack = techstackRepository.save(spring);

        //When
        UserTechstack userTechstack1 = addUserTeckstack(saveUser, saveTechstack);
        UserTechstack saved = userTechstackRepository.save(userTechstack1);

        //Then
        assertThat(saved.getUser()).isEqualTo(saveUser);
        assertThat(saved.getTechstack()).isEqualTo(saveTechstack);
    }

    @Test
    void findByAllUser() throws Exception {
        //Given
        User user = makeUser();
        Techstack spring = makeTechstack(TechName.Spring);
        Techstack react = makeTechstack(TechName.React);
        User saveUser = userRepository.save(user);
        Techstack saveTechstack1 = techstackRepository.save(spring);
        Techstack saveTechstack2 = techstackRepository.save(react);

        //When
        UserTechstack userTechstack1 = addUserTeckstack(saveUser, saveTechstack1);
        UserTechstack userTechstack2 = addUserTeckstack(saveUser, saveTechstack2);
        UserTechstack saved1 = userTechstackRepository.save(userTechstack1);
        UserTechstack saved2 = userTechstackRepository.save(userTechstack2);

        //Then
        List<UserTechstack> techNames = userTechstackRepository.findAllByUser(saveUser).get();
        assertThat(techNames.size()).isEqualTo(2);
    }

    private static UserTechstack addUserTeckstack(User user, Techstack spring) {
        UserTechstack userTechstack = new UserTechstack();
        userTechstack.setUser(user);
        userTechstack.setTechstack(spring);
        return userTechstack;
    }

    private static Techstack makeTechstack(TechName name) {
        Techstack techstack = new Techstack();
        techstack.setTechName(name);
        return techstack;
    }

    User makeUser() {
        User user = User.builder()
                .username("kim")
                .area("gumi")
                .term(8)
                .track("mobile")
                .classNum(4)
                .profileImg("src")
                .password("password")
                .build();
        return user;
    }
}