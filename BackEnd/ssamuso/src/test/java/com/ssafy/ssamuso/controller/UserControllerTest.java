package com.ssafy.ssamuso.controller;

import com.ssafy.ssamuso.TestUtil;
import com.ssafy.ssamuso.domain.dto.TeammateInfoDTO;
import com.ssafy.ssamuso.service.UserServiceImlp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @Mock
    private UserServiceImlp userServiceImlp;

    @Test
    void 있는_유저를_검색할때() throws Exception {
        //Given
        Optional<TeammateInfoDTO> teammateInfoDTO = TestUtil.makeTeammateInfoDTO();
        doReturn(teammateInfoDTO).when(userServiceImlp).findTeammateByUsername("userA");
        UserController userController = new UserController(null, userServiceImlp);

        TeammateInfoDTO resultInfo = TestUtil.makeTeammateInfoDTO().get();
        //When
        TeammateInfoDTO userAInfo = userController.simpleInfo("userA");

        //Then

        assertThat(userAInfo).isEqualTo(resultInfo);
    }

    @Test
    void 없는_유저_검색할때() throws Exception {
        //Given
        doReturn(Optional.empty()).when(userServiceImlp).findTeammateByUsername("userB");
        UserController userController = new UserController(null, userServiceImlp);

        //When
        assertThatThrownBy(() -> userController.simpleInfo("userB"))
                .isInstanceOf(IllegalArgumentException.class);
    }

}