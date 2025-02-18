package org.framework.studyboard.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 게시글 작성 응답 DTO
 */
@Getter
@Setter
@NoArgsConstructor
public class BoardWritingResDTO {
    // 응답코드
    private int code;
    // 응답메시지
    private String message="";
}
