package com.study.config.security;

import com.study.dto.UserDto;
import com.study.repository.AuthRepository;
import com.study.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 스프링 시큐리티 인증을 처리하는 서비스
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final AuthRepository authRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto findUser = userRepository.selectById(username);

        if (findUser == null) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }

        List<GrantedAuthority> authorities =
                authRepository.selectUserAuth(username).stream()
                    .map(e -> new SimpleGrantedAuthority(e.getRole()))
                    .collect(Collectors.toList());

        return new CustomUserDetails(findUser, authorities);
    }
}
