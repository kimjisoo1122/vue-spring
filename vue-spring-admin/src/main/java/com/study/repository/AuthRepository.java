package com.study.repository;

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
     * 관지라 권한목록을 조회합니다.
     *
     * @param adminId 사용자 아이디
     * @return 사용자 권한 목록
     */
    public List<Auth> selectAdminAuthList(String adminId) {
        return authMapper.selectAdminAuthList(adminId);
    }
}
