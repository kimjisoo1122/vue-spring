package com.study.repository;

import com.study.dto.AdminDto;
import com.study.mapper.AdminMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 'admin' 테이블 리포지토리
 */
@Repository
@RequiredArgsConstructor
public class AdminRepository {

    private final AdminMapper adminMapper;

    /**
     * 관리자를 조회합니다.
     *
     * @param adminId 관리자 아이디
     * @return 관리자 정보
     */
    public Optional<AdminDto> selectById(String adminId) {
        return Optional.ofNullable(adminMapper.selectById(adminId));
    }
}
