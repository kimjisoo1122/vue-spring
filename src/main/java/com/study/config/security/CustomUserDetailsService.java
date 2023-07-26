package com.study.config.security;

import com.study.dto.UserDto;
import com.study.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 스프링시큐리티의 로그인 인증을 처리하는 서비스
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        UserDto findUser = userRepository.selectUserById(userId);

        if (findUser == null) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }

        return new CustomUserDetails(findUser);
    }
}
