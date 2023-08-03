package com.study.controller;

import com.study.config.security.JwtAuthenticationProvider;
import com.study.dto.UserDto;
import com.study.dto.api.ResponseApiStatus;
import com.study.dto.api.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 로그인 API 컨트롤러
 */
@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    /**
     * 로그인에 성공시 jwt를 반환합니다.
     * @param user
     * @return
     */
    @PostMapping()
    public ResponseEntity<ResponseDto> login(
            @RequestBody UserDto user) {

        Authentication authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUserId(), user.getUserPw());

        try {
            authenticationManager.authenticate(authenticationToken);

            String accessJwt = jwtAuthenticationProvider.createJwt(user.getUserId(),
                    jwtAuthenticationProvider.ACCESS_TOKEN_EXPIRATION());

            ResponseDto response = new ResponseDto(ResponseApiStatus.SUCCESS);
            response.setData(accessJwt);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            ResponseDto failResponse = new ResponseDto(ResponseApiStatus.FAIL);
            failResponse.setErrorMessage("아이디 또는 비밀번호가 맞지 않습니다.");

            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(failResponse);
        }
    }
}
