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
 * 스프링 시큐리티를 설정하는 Config
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**")
                    .hasRole("ADMIN")
                .and()

                .formLogin()
                    .loginPage("/admin/login")
                    .usernameParameter("adminId")
                    .successHandler(new CustomSuccessHandler())
                    .failureHandler(new CustomFailureHandler())
                    .permitAll()
                .and()

                .logout()
                    .logoutUrl("/admin/logout")
                    .logoutSuccessUrl("/admin")
                    .invalidateHttpSession(true)
                .and()

                // TODO CSRF 설정
                .csrf().disable();

        return http.build();
    }

    /**
     * 정적리소스 필터
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring()
                .antMatchers("/admin/css/**", "/admin/js/**")
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    /**
     * 비밀번호를 암호화하는 인코더
     * @return BCryptPasswordEncoder
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
