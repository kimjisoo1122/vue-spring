package com.study.service;

import com.study.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

//    public UserDto findUserById(String userId) {
//        return userRepository.selectUserById(userId);
//    }
}
