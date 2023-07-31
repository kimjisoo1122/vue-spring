package com.study.config.security;

import com.study.dto.AdminDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 스프링 시큐리티 인증객체
 */
public class CustomUserDetails implements UserDetails {

    private final String userId;
    private final String userName;
    private final String userPw;
    private final List<GrantedAuthority> authorities = new ArrayList<>();

    public CustomUserDetails(AdminDto adminDto) {
        this.userId = adminDto.getAdminId();
        this.userName = adminDto.getAdminName();
        this.userPw = adminDto.getAdminPw();
    }

    public String getUserId() {
        return userId;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities.addAll(authorities);
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

    /**
     * @return 계정이 만료되지 않았는지 여부
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * @return 계정이 비활성화 되어있지 않았은지 여부
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * @return 계정의 비밀번호가 만료되지 않았는지 여부
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * @return 계정이 활성화되어 있는지 여부
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}