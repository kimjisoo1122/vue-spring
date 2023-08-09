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
 * JWT 인증 필터
 * 스프링 시큐리티 필터 앞단에 존재하여 JWT인증 후 인증객체를 세션에 주입합니다.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    /**
     * 인증이 필요한 경로에 대한 JWT인증을 거칩니다.
     * 인증에 실패한 경우 에러메시지를 반환 합니다.
     */
    private final List<String> AUTH_REQUIRED_PATHS =
            Arrays.asList("/api/replies", "/api/frees", "/api/galleries", "/api/qna");

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        log.info("처리 중인 요청 URI : {}", request.getRequestURI());

        if (isRequiredJwtAuth(request)) {
            String jwt = jwtAuthenticationProvider.getJwtFromHeader(request);
            log.info("JWT 인증 토큰 : {}", jwt);

            try {
                if (jwt == null) {
                    log.warn("토큰이 요청 헤더에 존재하지 않습니다.");
                    throw new JwtException("토큰이 존재하지 않습니다.");
                }

                Authentication authentication = jwtAuthenticationProvider.createAuthentication(jwt);
                log.info("JWT 인증 성공: {}", authentication.getPrincipal());

                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (JwtException e) {
                log.error("JWT 인증 실패: {}", e.getMessage());

                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.setCharacterEncoding("utf-8");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

                ResponseDto responseFail = new ResponseDto(ResponseApiStatus.FAIL);
                responseFail.setErrorMessage("유효하지 않은 토큰 입니다.");

                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.writeValue(response.getWriter(), responseFail);

                return;
            }
        }

        filterChain.doFilter(request, response);
    }
    /**
     * Jwt필터가 필요한지 체크합니다.
     *
     * @param request
     * @return
     */
    private boolean isRequiredJwtAuth(HttpServletRequest request) {
        boolean isAuthPath = AUTH_REQUIRED_PATHS.stream()
                .anyMatch(e -> request.getRequestURI().startsWith(e));

        boolean isGet = "GET".equalsIgnoreCase(request.getMethod());

        return isAuthPath && !isGet;
    }
}
