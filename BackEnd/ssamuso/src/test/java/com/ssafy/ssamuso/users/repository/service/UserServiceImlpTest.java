package com.ssafy.ssamuso.service;

import com.ssafy.ssamuso.TestUtil;
import com.ssafy.ssamuso.domain.dto.TeammateInfoDTO;
import com.ssafy.ssamuso.domain.entity.Portfolios;
import com.ssafy.ssamuso.domain.entity.User;
import com.ssafy.ssamuso.domain.entity.UserTechstack;
import com.ssafy.ssamuso.domain.entity.enumtype.TechName;
import com.ssafy.ssamuso.repository.PortfoliosRepository;
import com.ssafy.ssamuso.repository.UserDeleteRepository;
import com.ssafy.ssamuso.repository.UserRepository;
import com.ssafy.ssamuso.repository.UserTechstackRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserServiceImlpTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PortfoliosRepository portfoliosRepository;
    @Mock
    private UserTechstackRepository userTechstackRepository;
    @Mock
    private UserDeleteRepository userDeleteRepository;

    @BeforeEach
    void mocking() {
        Optional<User> user = TestUtil.makeUser();
        Mockito.doReturn(user).when(userRepository).findByUsername(user.get().getUsername());

        Optional<Portfolios> portfolios = TestUtil.makePortfolios(user.get());
        Mockito.doReturn(portfolios).when(portfoliosRepository).findByUser(user.get());

        Optional<List<UserTechstack>> userTechstacks = TestUtil.makeUserTechStacks();
        Mockito.doReturn(userTechstacks).when(userTechstackRepository).findAllByUser(user.get());
    }

    @Test
    void 팀메이트_정보_확인() throws Exception {
        //Given
        UserServiceImlp userServiceImlp = new UserServiceImlp(userRepository, portfoliosRepository, userTechstackRepository, null);

        //When
        Optional<TeammateInfoDTO> userAInfo = userServiceImlp.findTeammateByUsername("userA");

        //Then
        TeammateInfoDTO teammateInfoDTO = new TeammateInfoDTO();
        teammateInfoDTO.setPortfoliosLink("link");
        teammateInfoDTO.setUsername("userA");
        teammateInfoDTO.setTechstacks(List.of(TechName.Spring, TechName.React));
        assertThat(userAInfo.get()).isEqualTo(teammateInfoDTO);
    }

    @Test
    void 없는_유저이름() throws Exception {
        //Given
        UserServiceImlp userServiceImlp = new UserServiceImlp(userRepository, portfoliosRepository, userTechstackRepository, null);

        //When
        Optional<TeammateInfoDTO> userAInfo = userServiceImlp.findTeammateByUsername("userB");

        //Then
        assertThat(userAInfo.isEmpty()).isTrue();
    }

}