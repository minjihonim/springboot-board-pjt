package org.prac.board.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_board")
@NoArgsConstructor
@Getter
public class Board {
    @Id
    @GeneratedValue
    private Long id;    // 게시글 넘버

    private String subject; // 제목

    private String writer;  // 작성자명

    private String content; // 작성 내용
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDt;    // 작성 날짜
}
