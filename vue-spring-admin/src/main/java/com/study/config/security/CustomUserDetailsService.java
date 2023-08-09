package com.study.config.security;

import com.study.repository.AdminRepository;
import com.study.repository.AuthRepository;
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
 * 스프링 시큐리티 로그인 인증을 처리하는 서비스
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final AdminRepository adminRepository;
    private final AuthRepository authRepository;

    /**
     * 로그인폼에서 입력받은 ID로 회원정보를 조회합니다.
     * 반환한 CustomUserDetails는 AuthenticationProvider에 기본으로 등록되어있는
     * AuthenticationManger에서 입력받은 비밀번호를 암호화하여 사용자정보와 비교하여 성공/실패를 처리합니다.
     * 실패 한 경우 CustomFailureHandler를 통해 로그인폼으로 리다이렉트 합니다.
     *
     * @param adminId 관리자 아이디
     * @throws UsernameNotFoundException 관리자 아이디가 존재하지 않는 경우 발생하는 예외
     */
    @Override
    public UserDetails loadUserByUsername(String adminId) throws UsernameNotFoundException {
        return adminRepository.selectById(adminId)
                .map(admin -> {
                    List<GrantedAuthority> collect = authRepository.selectAdminAuthList(admin.getAdminId()).stream()
                            .map(auth -> new SimpleGrantedAuthority(auth.getRole()))
                            .collect(Collectors.toList());

                    return new CustomUserDetails(admin, collect);
                })
                .orElseThrow(() -> {
                    log.info("해당 관리자를 찾을 수 없습니다. 입력 아이디 : {}" , adminId);

                    throw new UsernameNotFoundException("관리자를 찾을 수 없습니다.");
                });
    }
}
