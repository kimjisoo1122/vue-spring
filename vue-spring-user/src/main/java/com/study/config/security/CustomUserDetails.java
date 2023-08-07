package com.study.config.security;

import com.study.dto.UserDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * 스프링 시큐리티 인증된 사용자 객체
 */
public class CustomUserDetails implements UserDetails {

    private final String userId;
    private final String userName;
    private final String userPw;
    private final List<GrantedAuthority> authorities;

    public CustomUserDetails(UserDto user , List<GrantedAuthority> authorities) {
        this.userId = user.getUserId();
        this.userName = user.getUserName();
        this.userPw = user.getUserPw();
        this.authorities = authorities;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return userPw;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
