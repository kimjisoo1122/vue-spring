package com.study.config.security;

import com.study.repository.AdminRepository;
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
 * 스프링시큐리티의 로그인 인증을 처리하는 서비스
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final AdminRepository adminRepository;

    /**
     * 로그인폼에서 받아온 ID로 회원정보를 조회하여
     * CustomUserDetails에 사용자정보를 담으면
     * AuthenticationProvider에서 입력받은 비밀번호를 encrypt하여
     * 사용자정보와 비교하여 성공/실패를 처리한다.
     * @param adminId 관리자 아이디
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String adminId) throws UsernameNotFoundException {
        CustomUserDetails userDetails = adminRepository.selectById(adminId)
                .map(CustomUserDetails::new)
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("관리자를 찾을 수 없습니다.");
                });

        List<GrantedAuthority> authorities =
                adminRepository.selectAuthById(adminId).stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());

        userDetails.setAuthorities(authorities);

        return userDetails;
    }
}
