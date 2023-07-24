package com.study.config.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * 스프링 시큐리티를 설정하는 Config 입니다.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin**")
                    .hasRole("ADMIN") // 모든 요청을 관리자권한이 존재해야한다.
                .and()
                .formLogin()
                    .loginPage("/admin/login")
                    .usernameParameter("userId")
                    .successHandler(new CustomSuccessHandler())
                    .failureHandler(new CustomFailureHandler())
                .and()
                .logout()
                    .logoutUrl("/admin/logout")
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true)
                .and()
                .csrf().disable();

        // 스프링시큐리티는 기본적으로 get을 제외한 http요청에 csrf토큰을 주고 받아 보안함
        // csrf공격이란 ? 해당사이트의 인증된 유저를 대상으로 타 사이트의 스크립트코드를 클릭유도하여
        // 권한을 가진 대상으로 하여금 공격대상사이트에 의도치않은 스크립트 요청
        // xss공격이란 ? 스크립트를 클릭유도하여 유저의 정보 탈취 및 서버에 의도치않은 스크립트 요청
        // 기본적으로 세션을 사용하지 않는다는건 해당유저의 세션탈취(xss공격)에 안전
        // jwt를 쿠키에 담아 쿠키를 httponly또는 secure(https)처리하여 스크립트로는 서버에 요청 불가
        return http.build();
    }

    /**
     * 정적리소스요청에 대해 스프링시큐리티 정책을 적용하지않는다.
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    /**
     * 비밀번호를 암호화하는 인코더 입니다.
     * @return BCryptPasswordEncoder
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
