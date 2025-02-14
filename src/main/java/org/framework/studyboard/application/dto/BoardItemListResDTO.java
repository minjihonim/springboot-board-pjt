package org.prac.board.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.prac.board.domain.model.Board;

import java.time.LocalDateTime;

/**
 * 게시글 목록 DTO
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardItemListResDTO {
    // 제목
    private String subject;
    // 작성자명
    private String writer;
    // 작성 내용
    private String content;
    // 작성 날짜
    private LocalDateTime date;
}
