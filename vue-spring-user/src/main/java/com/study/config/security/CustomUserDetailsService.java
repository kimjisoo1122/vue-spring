package com.study.config.security;

import com.study.repository.AuthRepository;
import com.study.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final AuthRepository authRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        return userRepository.selectById(userId)
                .map(user -> {
                    List<GrantedAuthority> collect = authRepository.selectUserAuthList(user.getUserId()).stream()
                            .map(auth -> new SimpleGrantedAuthority(auth.getRole()))
                            .collect(Collectors.toList());

                    return new CustomUserDetails(user, collect);
                })
                .orElseThrow(() -> {
                    log.info("해당 사용자를 찾을 수 없습니다. 입력 아이디 : {}" , userId);

                    throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
                });
    }
}
