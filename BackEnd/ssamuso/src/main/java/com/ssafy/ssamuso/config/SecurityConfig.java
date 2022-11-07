package com.ssafy.ssamuso.config;

import com.ssafy.ssamuso.service.PrincipalOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final PrincipalOAuth2UserService principalOauth2UserService;

    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .anyRequest().permitAll();
        http.headers()
                .frameOptions()
                .sameOrigin();
        http.oauth2Login()
                .loginPage("/users")
                .defaultSuccessUrl("/")
                .userInfoEndpoint() // 필수
                .userService(principalOauth2UserService);
        return http.build();
    }
}
