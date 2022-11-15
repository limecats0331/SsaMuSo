package com.ssafy.ssamuso.config;

import com.ssafy.ssamuso.service.PrincipalOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final PrincipalOAuth2UserService principalOauth2UserService;

/*    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // Access-Control-Allow-Origin 값을 설정한다.
        // setAllowedOrigins로 여러개를 한꺼번에 설정할 수 도 있다.
        corsConfiguration.addAllowedOrigin(origins);
        // 어떤 HTTP 메서드를 허용할지 정할 수 있다.
        // setAllowedMethods로 여러개를 한꺼번에 설정할 수 있다.
        corsConfiguration.addAllowedMethod("*");
        // 허용할 헤더를 설정한다.
        corsConfiguration.addAllowedHeader("*");
        // 사용자 인증이 필요할 때 설정해줘야한다.
        // true면 브라우저에서 쿠키를 보내 사용자 인증이 필요한 리소스에 접근할 수 있다.
        corsConfiguration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        // 허용할 path 설정
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return urlBasedCorsConfigurationSource;
    }*/

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .anyRequest().permitAll();
        http.headers()
                .frameOptions()
                .sameOrigin();
//        http.oauth2Login()
//                .loginPage("/login")
//                .defaultSuccessUrl("/")
//                .userInfoEndpoint() // 필수
//                .userService(principalOauth2UserService);
        http.logout().disable();

        http.csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
        return http.build();
    }


}
