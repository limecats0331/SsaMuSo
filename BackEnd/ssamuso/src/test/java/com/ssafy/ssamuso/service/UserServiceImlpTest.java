package com.ssafy.ssamuso.service;

import com.ssafy.ssamuso.TestUtil;
import com.ssafy.ssamuso.domain.dto.IdentificationDto;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

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
        doReturn(user).when(userRepository).findByUsername(user.get().getUsername());

        Optional<Portfolios> portfolios = TestUtil.makePortfolios(user.get());
        doReturn(portfolios).when(portfoliosRepository).findByUser(user.get());

        Optional<List<UserTechstack>> userTechstacks = TestUtil.makeUserTechStacks();
        doReturn(userTechstacks).when(userTechstackRepository).findAllByUser(user.get());

        List<String> keyList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            keyList.add(String.valueOf(i));
        }
        doReturn(keyList).when(userRepository).findRoutingKeyByUsername(any(String.class));
    }

    @Test
    void 팀메이트_정보_확인() throws Exception {
        //Given
        UserServiceImpl userServiceImpl = new UserServiceImpl(userRepository, portfoliosRepository, userTechstackRepository, null);

        //When
        Optional<TeammateInfoDTO> userAInfo = userServiceImpl.findTeammateByUsername("userA");

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
        UserServiceImpl userServiceImpl = new UserServiceImpl(userRepository, portfoliosRepository, userTechstackRepository, null);

        //When
        Optional<TeammateInfoDTO> userAInfo = userServiceImpl.findTeammateByUsername("userB");

        //Then
        assertThat(userAInfo.isEmpty()).isTrue();
    }

    @Test
    void identify() {
        //given
        IdentificationDto test1 = new IdentificationDto("test", "0");
        IdentificationDto test2 = new IdentificationDto("test", "7");
        UserServiceImpl userService = new UserServiceImpl(userRepository, portfoliosRepository, userTechstackRepository, null);

        //when
        boolean identify1 = userService.identify(test1);
        boolean identify2 = userService.identify(test2);

        //then
        assertThat(identify1).isTrue();
        assertThat(identify2).isFalse();
    }

}