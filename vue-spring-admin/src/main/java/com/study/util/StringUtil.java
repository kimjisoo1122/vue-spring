package com.study.util;

/**
 * 문자열 Util
 */
public interface StringUtil {

    /**
     * 값이 null이면 ""반환 외 기존값 반환
     * @param value 입력 값
     * @return "" or value
     */
    static String nvl(String value) {
        if (value == null || value.trim().length() == 0) {
            return "";
        } else {
            return value;
        }
    }

    /**
     * 값이 null이면 ifNull반환 외 기존값 반환합니다.
     * @param value 입력 문자열
     * @param ifNull null 대체 문자열
     * @return ifNull or value
     */
    static String nvl(String value, String ifNull) {
        if (value == null || value.trim().length() == 0) {
            return ifNull;
        } else {
            return value;
        }
    }
}
