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

    /**
     * 로그인폼에서 받아온 ID로 회원정보를 조회하여
     * CustomUserDetails에 사용자정보를 담으면
     * AuthenticationProvider에서 입력받은 비밀번호를 encrypt하여
     * //TODO 추후 JWT사용시 Provider를 커스텀하여 토큰을 확인하여 인증객체 발급과정 추가
     * 사용자정보와 비교하여 성공/실패를 처리한다.
     * @param userId 사용자아이디
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        UserDto findUser = userRepository.selectById(userId);

        if (findUser == null) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }

        return new CustomUserDetails(findUser);
    }
}
