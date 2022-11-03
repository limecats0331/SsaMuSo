package com.ssafy.ssamuso.service;

import com.ssafy.ssamuso.security.provider.GoogleOAuthUserInfo;
import com.ssafy.ssamuso.security.provider.NaverOAuthUserInfo;
import com.ssafy.ssamuso.security.provider.OAuthUserInfo;
import com.ssafy.ssamuso.security.user.OAuth2UserImpl;
import com.ssafy.ssamuso.users.domain.User;
import com.ssafy.ssamuso.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PrincipalOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = getOAuth2User(userRequest);

        OAuthUserInfo oAuthUserInfo = null;

        String provider = userRequest.getClientRegistration().getRegistrationId();

        if (provider.equals("google")) {
            oAuthUserInfo = new GoogleOAuthUserInfo(oAuth2User.getAttributes());
        } else if (provider.equals("naver")) {
            oAuthUserInfo = new NaverOAuthUserInfo(oAuth2User.getAttribute("response"));
        }
        String providerId = oAuthUserInfo != null ? oAuthUserInfo.getProviderId() : "";
        String username = provider + "_" + providerId;
        String email = oAuthUserInfo.getEmail();
        String name = oAuthUserInfo != null ? oAuthUserInfo.getName() : "";
        String password = UUID.randomUUID().toString();

        Optional<User> findUser = userRepository.findByUsername(username);

        User user;

        if (findUser.isEmpty()) {
            user = new User(username, name, email, password, passwordEncoder);
            userRepository.save(user);
        } else {
            user = findUser.get();
        }

        return new OAuth2UserImpl(user, oAuthUserInfo);

    }

    public OAuth2User getOAuth2User(OAuth2UserRequest userRequest) {
        return super.loadUser(userRequest);
    }
}
