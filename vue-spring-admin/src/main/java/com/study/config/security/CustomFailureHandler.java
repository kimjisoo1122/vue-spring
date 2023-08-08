package com.study.config.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 스프링 시큐리티 인증에 실패 할 경우 처리하는 핸들러
 * 로그인 컨트롤러로 리다이렉트 하여 로그인 폼으로 리다이렉트 합니다.
 */
public class CustomFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {

        // 로그인 Form에 입력했던 아이디 재전송
        request.getSession().setAttribute("userId", request.getParameter("userId"));

        response.sendRedirect("/admin/login");
    }
}
