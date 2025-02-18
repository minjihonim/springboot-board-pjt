package org.framework.studyboard.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 게시글작성 요청 DTO
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardWritingReqDTO {

    // 제목
    private String subject;
    // 작성자명
    private String writer;
    // 작성 내용
    private String content;
    // 비밀번호
    private String password;
    
}
