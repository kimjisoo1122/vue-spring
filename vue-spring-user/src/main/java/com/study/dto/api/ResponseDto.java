package com.study.dto.api;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * API 응답에 사용되는 DTO
 * {
 *     statues : status,
 *     errorMessage : errorMessage,
 *     data : {
 *
 *     }
 * }
 */
@Data
@NoArgsConstructor
public class ResponseDto {

    public ResponseDto(ResponseApiStatus status) {
        this.status = status;
    }

    private ResponseApiStatus status; // API 응답상태
    private String errorMessage = ""; // 응답실패 : 예외 메시지
    private Object data; // 응답 데이터
}
