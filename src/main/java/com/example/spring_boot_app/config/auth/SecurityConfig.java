package com.example.spring_boot_app.config.auth;

import com.example.spring_boot_app.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // CSRF 비활성화
                .headers(headers -> headers.frameOptions(frame -> frame.disable())) // h2-console 사용 가능하게 설정
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll()
                        .requestMatchers("/api/v1/**").hasRole(Role.USER.name())
                        //.anyRequest().authenticated()
                        .anyRequest().permitAll() //모든 요청을 인증 없이 허용
                )
                .logout(logout -> logout.logoutSuccessUrl("/")); // 로그아웃 성공 시 홈으로 이동

        // OAuth2 로그인 관련 설정 제거
        //.oauth2Login(oauth2 -> oauth2
        //        .userInfoEndpoint(userInfo -> userInfo
        //                .userService(customOAuth2UserService)
        //        )
        //);

        return http.build();
    }
}