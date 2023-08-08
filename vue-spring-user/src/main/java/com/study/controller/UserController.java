package com.study.controller;

import com.study.dto.UserDto;
import com.study.dto.api.ResponseApiStatus;
import com.study.dto.api.ResponseDto;
import com.study.dto.api.ResponseValidFormDto;
import com.study.exception.UserNotFoundException;
import com.study.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 사용자 API 컨트롤러
 */
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    /**
     * 회원가입을 진행합니다.
     * /TODO 회원가입 예외처리
     *
     * @param user 사용자 정보
     * @param bindingResult 유효성검증객체
     */
    @PostMapping()
    public ResponseEntity<ResponseDto> signUp(
            @Validated @RequestBody UserDto user,
            BindingResult bindingResult) {

        if (user.getUserId().equals(user.getUserPw())) {
            bindingResult.rejectValue("userPw", null, "아이디랑 동일할 수 없습니다.");
        }

        if (bindingResult.hasErrors()) {
            return ResponseEntity
                    .badRequest()
                    .body(createValidFormResponse(bindingResult));
        }

        String signedId = userService.signUp(user);
        ResponseValidFormDto response = new ResponseValidFormDto(ResponseApiStatus.SUCCESS);
        response.setData(signedId);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    /**
     * 가입하려는 아이디의 중복체크를 합니다.
     *
     * @param userId 가입 아이디
     */
    @GetMapping("/exits")
    public ResponseEntity<ResponseDto> exitsUserId(
            @RequestParam("userId") String userId) {

        ResponseDto response = new ResponseDto(ResponseApiStatus.SUCCESS);
        response.setData(userService.doubleCheckId(userId));

        return ResponseEntity.ok(response);
    }

    /**
     * 사용자 아이디로 사용자정보를 조회합니다.
     *
     * @param userId 사용자 아이디
     */
    @GetMapping("/{userId}")
    public ResponseEntity<ResponseDto> userDetail(
            @PathVariable("userId") String userId) {

        UserDto findUser = userService.findById(userId).orElseThrow(UserNotFoundException::new);
        findUser.setUserPw("");

        ResponseDto response = new ResponseDto(ResponseApiStatus.SUCCESS, findUser);

        return ResponseEntity.ok(response);
    }

    /**
     * 유효성검증에 실패한 Form데이터의 에러값을 저장해서 반환합니다.
     * @param bindingResult 유효성검증객체
     * @return ResponseValidFormDto
     */
    private ResponseValidFormDto createValidFormResponse(BindingResult bindingResult) {
        ResponseValidFormDto response = new ResponseValidFormDto(ResponseApiStatus.FAIL);
        response.setErrorMessage("잘못된 데이터입니다.");

        // 에러필드이름과 에러메시지를 응답값에 담습니다.
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            response.getErrorFields().put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return response;
    }
}
