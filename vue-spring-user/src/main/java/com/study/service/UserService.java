package com.study.service;

import com.study.dto.UserDto;
import com.study.enums.Auth;
import com.study.repository.AuthRepository;
import com.study.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * 사용자 서비스
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    private final AuthRepository authRepository;

    /**
     * 사용자 아이디를 등록합니다.
     * 사용자 권한을 등록합니다.
     *
     * @param user 사용자 정보
     * @return 등록된 사용자 아이디
     */
    @Transactional
    public String signUp(UserDto user) {
        UserDto encryptedUser = encryptUserPw(user);
        userRepository.insert(encryptedUser);

        encryptedUser.setUserAuth(Auth.USER);
        authRepository.insertUserAuth(encryptedUser);

        return user.getUserId();
    }

    /**
     * 아이디를 중복확인 합니다.
     *
     * @param userId 사용자 아이디
     * @return 아이디가 존재하지 않으면 true
     */
    public boolean doubleCheckId(String userId) {
        return userRepository.selectById(userId).isEmpty();
    }

    /**
     * 사용자 정보를 조회합니다.
     *
     * @param userId 사용자 아이디
     * @return 사용자 정보
     */
    public Optional<UserDto> findById(String userId) {
        return userRepository.selectById(userId);
    }

    /**
     * 사용자 비밀번호를 암호화합니다.
     * @param user 사용자 객체
     * @return 암호화된 사용자 객체
     */
    private UserDto encryptUserPw(UserDto user) {
        String userPw = user.getUserPw();
        user.setUserPw(bCryptPasswordEncoder.encode(userPw));

        return user;
    }
}
