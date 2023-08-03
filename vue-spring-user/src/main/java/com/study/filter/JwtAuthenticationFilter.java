package com.study.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.config.security.JwtAuthenticationProvider;
import com.study.dto.api.ResponseApiStatus;
import com.study.dto.api.ResponseDto;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * jwt 인증 필터
 * 스프링시큐리티 필터 앞단에 존재하여 jwt인증 후 인증객체를 세션에 주입합니다.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    /**
     * 필터제외 요청
     */
    private final List<String> EXCLUDED_PATHS =
            Arrays.asList("/api/login", "/api/sign", "/api/file", "/api/reply/board");
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        // 로그인, 회원가입 요청이면 컨트롤러로 넘깁니다.
        boolean isExcludePath = EXCLUDED_PATHS.stream().anyMatch(e -> request.getRequestURI().startsWith(e));
        if (isExcludePath) {
            log.info("jwt filter exclude path uri = {}", request.getRequestURI());
            filterChain.doFilter(request, response);
            return;
        }

        String jwt = jwtAuthenticationProvider.getJwtFromHeader(request);

        try {
            if (jwt == null) {
                throw new JwtException("토큰이 존재하지 않습니다.");
            }

            Authentication authentication = jwtAuthenticationProvider.getAuthentication(jwt);

            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (JwtException e) {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding("utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            ResponseDto responseFail = new ResponseDto(ResponseApiStatus.FAIL);
            responseFail.setErrorMessage("유효하지 않은 토큰 입니다.");

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(response.getWriter(), responseFail);

            return;
        }

        filterChain.doFilter(request, response);
    }

}
