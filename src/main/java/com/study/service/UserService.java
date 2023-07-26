package com.study.service;

import com.study.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 사용자 서비스
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
}
