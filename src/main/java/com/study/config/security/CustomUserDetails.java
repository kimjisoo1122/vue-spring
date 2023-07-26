package com.study.config.security;

import com.study.enums.UserAuth;
import com.study.dto.UserDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private final String userId;
    private final String userName;
    private final String userPw;
    private final List<GrantedAuthority> authorities = new ArrayList<>();

    public CustomUserDetails(UserDto userDto) {
        this.userId = userDto.getUserId();
        this.userName = userDto.getUserName();
        this.userPw = userDto.getUserPw();

        setAuthorities(userDto.getUserAuth());
    }

    public void setAuthorities(UserAuth userAuth) {
        if (userAuth == UserAuth.ADMIN) {
            authorities.add(new SimpleGrantedAuthority(UserAuth.ADMIN.getValue()));
        }

        authorities.add(new SimpleGrantedAuthority(UserAuth.USER.getValue()));
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
