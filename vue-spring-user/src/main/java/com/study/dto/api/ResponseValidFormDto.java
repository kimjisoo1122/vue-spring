package com.study.dto.api;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * Form 데이터 유효성검증을 위한 API 응답 DTO 입니다.
 * {
 *     statues : status,
 *     errorMessage : errorMessage,
 *     errorFields : {
 *         errorField : defaultMessage
 *     }
 *     data : {
 *
 *     }
 * }
 */
@Data
public class ResponseValidFormDto extends ResponseDto {

    public ResponseValidFormDto(ResponseApiStatus status) {
        super(status);
    }

    public ResponseValidFormDto(ResponseApiStatus status, Object data) {
        super(status, data);
    }

    private Map<String, String> errorFields = new HashMap<>(); // 에러필드
}
