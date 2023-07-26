package com.study.util;

import com.study.config.security.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public interface SecurityUtil {

    static CustomUserDetails getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        CustomUserDetails user = null;
        if (authentication != null &&
                authentication.getPrincipal() instanceof CustomUserDetails) {
            user = (CustomUserDetails) authentication.getPrincipal();
        }
        return user;
    }

    static String getUserId() {
        CustomUserDetails user = getAuthenticatedUser();

        return user == null ? "" : user.getUserId();
    }

    static String getUserName() {
        CustomUserDetails user = getAuthenticatedUser();

        return user == null ? "" : user.getUsername();
    }
}
