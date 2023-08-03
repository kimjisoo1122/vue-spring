package com.study.controller;

import com.study.dto.UserDto;
import com.study.dto.api.ResponseApiStatus;
import com.study.dto.api.ResponseDto;
import com.study.dto.api.ResponseValidFormDto;
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

        ResponseValidFormDto response = new ResponseValidFormDto(ResponseApiStatus.SUCCESS);
        response.setData(userService.signUp(user));

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
     * 1. 맨먼저 VUE단에서 라우터가드를 통해서 인증이 필요한값에 한해서 로그인 페이지로 보낸다.
     * 2. 로그인에 성공하면 STORE의 STATE의 현재상태를 저장해놔야 함
     * 3. 홈페이지간의 이동은 -> VUE의 라우터가드로 검증
     * 4. API요청은 서버에서 검증
     * 5. jwt필터에서 아디랑 비번 파싱해서 유저패스워드 토큰을 생성한다
     * 6. 토큰을 authenticationmanager한테 넘김
     * 7. authenticationmanager는 유저디테일서비스에서 입력받은 아이디로 실제비밀번호를 조회한다.
     * 8. 유저디테일서비스에서 조회한 아이디와 비밀번호를 바탕으로 userdetails를 반환하면
     * 9. 프로바이더는 내가만든 토큰의 비밀번호를 암호화하여 생성된 userdetail와 비교하여 인증을 처리한다.
     * 10. 인증에성공하면 userdetails가 principal로 저장되고 , 석세스핸들러로 넘어감
     * @param user
     * @return
     */
    public ResponseDto login(
            @RequestBody() UserDto user) {


        return null;
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
