package org.framework.studyboard.global.common;

import lombok.Getter;

/**
 * 응답 코드 정의
 */
@Getter
public enum ErrorCode {
    SUCCESS(0000, "성공"),
    ;

    private int code;

    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
