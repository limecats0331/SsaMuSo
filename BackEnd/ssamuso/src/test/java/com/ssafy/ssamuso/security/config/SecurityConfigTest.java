package com.ssafy.ssamuso.security.config;

import com.ssafy.ssamuso.service.PrincipalOAuth2UserService;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;

import static org.junit.jupiter.api.Assertions.*;

@MockBeans({
        @MockBean(PrincipalOAuth2UserService.class)
})
class SecurityConfigTest {


}