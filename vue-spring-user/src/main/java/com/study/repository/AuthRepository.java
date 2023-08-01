package com.study.repository;

import com.study.dto.UserDto;
import com.study.enums.Auth;
import com.study.mapper.AuthMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 'auth' 테이블 리포지토리
 */
@Repository
@RequiredArgsConstructor
public class AuthRepository {

    private final AuthMapper authMapper;

    /**
     * 사용자 권한을 등록합니다.
     *
     * @param user 사용자 정보
     */
    public void insertUserAuth(UserDto user) {
        authMapper.insertUserAuth(user);
    }

    /**
     * 사용자 권한을 조회합니다.
     *
     * @param userId 사용자 아이디
     * @return 사용자 권한 목록
     */
    public List<Auth> selectUserAuth(String userId) {
        return authMapper.selectUserAuth(userId);
    }
}
