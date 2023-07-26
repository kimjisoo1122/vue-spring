package com.study.config.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 스프링 시큐리티 인증에 실패할경우 처리하는 핸들러
 */
public class CustomFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {

        // LoginController로 입력했던 ID를 넘겨줍니다.
        request.getSession().setAttribute("userId", request.getParameter("userId"));

        response.sendRedirect("/admin/login");
    }
}
