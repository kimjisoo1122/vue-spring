package com.study.util;

import com.study.config.security.CustomUserDetails;
import com.study.dto.BoardForm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 스프링시큐리티 관련 유틸
 */
public interface SecurityUtil {

    /**
     * 세션에 저장된 인증된 사용자객체를 조회합니다.
     * @return CustomUserDetails 인증된 사용자정보
     */
    static CustomUserDetails getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        CustomUserDetails user = null;
        if (authentication != null &&
                authentication.getPrincipal() instanceof CustomUserDetails) {
            user = (CustomUserDetails) authentication.getPrincipal();
        }

        return user;
    }

    /**
     * 현재 인증된 사용자의 ID를 반환합니다.
     * @return 인증된 사용자가 없을 경우 빈 문자열을 반환하고, 그렇지 않은 경우 사용자 ID를 반환합니다.
     */
    static String getUserId() {
        CustomUserDetails user = getAuthenticatedUser();

        return user == null ? "" : user.getUserId();
    }

    /**
     *  현재 인증된 사용자의 이름을 반환합니다.
     * @return 인증된 사용자가 없을 경우 빈 문자열을 반환하고, 그렇지 않은 경우 사용자 이름을 반환합니다.
     */
    static String getUserName() {
        CustomUserDetails user = getAuthenticatedUser();

        return user == null ? "" : user.getUsername();
    }

    /**
     * 스프링 시큐리티 인증객체로부터 사용자정보를 가져와 폼 데이터를 설정합니다.
     * @param form 게시글폼
     */
    static void setFormUser(BoardForm form) {
        form.setUserId(SecurityUtil.getUserId());
        form.setUserName(SecurityUtil.getUserName());
    }
}
