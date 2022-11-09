package com.ssafy.ssamuso.users.repository.service;

import com.ssafy.ssamuso.domain.entity.User;
import com.ssafy.ssamuso.repository.UserRepository;
import com.ssafy.ssamuso.service.PrincipalOAuth2UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PrincipalOAuth2UserServiceTestImlp {

    @Mock
    OAuth2UserRequest userRequest;

    @Mock
    ClientRegistration clientRegistration;

    @Mock
    ClientRegistration.ProviderDetails providerDetails;

    @Mock
    ClientRegistration.ProviderDetails.UserInfoEndpoint userInfoEndpoint;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Mock
    UserRepository userRepository;

    @Mock
    DefaultOAuth2User defaultOAuth2User;

    @Test
    void noGoogleUserInDB() {

        //given
        doReturn(clientRegistration).when(userRequest).getClientRegistration();
        doReturn("google").when(clientRegistration).getRegistrationId();
        doReturn(null).when(userRepository).save(any(User.class));
        doReturn(Optional.empty()).when(userRepository).findByUsername(anyString());
        PrincipalOAuth2UserService principalOAuth2UserService = new PrincipalOAuth2UserService(userRepository, passwordEncoder);
        PrincipalOAuth2UserService spy = spy(principalOAuth2UserService);
        Map<String, Object> map = new HashMap<>();
        map.put("sub", "google");
        map.put("given_name", "asdlfkj");
        map.put("email", "asdoifj@aossdfoij");
        doReturn(map).when(defaultOAuth2User).getAttributes();
        doReturn(defaultOAuth2User)
                .when(spy).getOAuth2User(userRequest);

        //when
        OAuth2User oAuth2User = spy.loadUser(userRequest);

        //then
        assertThat(oAuth2User.getAttributes()).isEqualTo(map);
        verify(userRepository).save(any(User.class));
        verify(defaultOAuth2User).getAttributes();
        verify(defaultOAuth2User, never()).getAttribute(any());
    }

    @Test
    void googleUserInDB() {
        //given
        doReturn(clientRegistration).when(userRequest).getClientRegistration();
        doReturn("google").when(clientRegistration).getRegistrationId();
        doReturn(Optional.of(User.builder().
                id(1L).
                build())).when(userRepository).findByUsername(anyString());
        PrincipalOAuth2UserService principalOAuth2UserService = new PrincipalOAuth2UserService(userRepository, passwordEncoder);
        PrincipalOAuth2UserService spy = spy(principalOAuth2UserService);
        Map<String, Object> map = new HashMap<>();
        map.put("sub", "google");
        map.put("given_name", "asdlfkj");
        map.put("email", "asdoifj@aossdfoij");
        doReturn(map).when(defaultOAuth2User).getAttributes();
        doReturn(defaultOAuth2User)
                .when(spy).getOAuth2User(userRequest);

        //when
        OAuth2User oAuth2User = spy.loadUser(userRequest);

        //then
        assertThat(oAuth2User.getAttributes()).isEqualTo(map);
        verify(userRepository, never()).save(any(User.class));
        verify(defaultOAuth2User).getAttributes();
        verify(defaultOAuth2User, never()).getAttribute(any());
    }

    @Test
    void noNaverUserInDB() {

        //given
        doReturn(clientRegistration).when(userRequest).getClientRegistration();
        doReturn("naver").when(clientRegistration).getRegistrationId();
        doReturn(null).when(userRepository).save(any(User.class));
        doReturn(Optional.empty()).when(userRepository).findByUsername(anyString());
        PrincipalOAuth2UserService principalOAuth2UserService = new PrincipalOAuth2UserService(userRepository, passwordEncoder);
        PrincipalOAuth2UserService spy = spy(principalOAuth2UserService);
        Map<String, Object> map = new HashMap<>();
        map.put("sub", "google");
        map.put("given_name", "asdlfkj");
        map.put("email", "asdoifj@aossdfoij");
        doReturn(map).when(defaultOAuth2User).getAttribute("response");
        doReturn(defaultOAuth2User)
                .when(spy).getOAuth2User(userRequest);

        //when
        OAuth2User oAuth2User = spy.loadUser(userRequest);

        //then
        assertThat(oAuth2User.getAttributes()).isEqualTo(map);
        verify(userRepository).save(any(User.class));
        verify(defaultOAuth2User).getAttribute("response");
        verify(defaultOAuth2User, never()).getAttributes();
    }

    @Test
    void naverUserInDB() {
        //given
        doReturn(clientRegistration).when(userRequest).getClientRegistration();
        doReturn("naver").when(clientRegistration).getRegistrationId();
        doReturn(Optional.of(User.builder().
                id(1L).
                build())).when(userRepository).findByUsername(anyString());
        PrincipalOAuth2UserService principalOAuth2UserService = new PrincipalOAuth2UserService(userRepository, passwordEncoder);
        PrincipalOAuth2UserService spy = spy(principalOAuth2UserService);
        Map<String, Object> map = new HashMap<>();
        map.put("sub", "google");
        map.put("given_name", "asdlfkj");
        map.put("email", "asdoifj@aossdfoij");
        doReturn(map).when(defaultOAuth2User).getAttribute("response");
        doReturn(defaultOAuth2User)
                .when(spy).getOAuth2User(userRequest);

        //when
        OAuth2User oAuth2User = spy.loadUser(userRequest);

        //then
        assertThat(oAuth2User.getAttributes()).isEqualTo(map);
        verify(userRepository, never()).save(any(User.class));
        verify(defaultOAuth2User).getAttribute("response");
        verify(defaultOAuth2User, never()).getAttributes();
    }

}