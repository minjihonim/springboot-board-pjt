package org.prac.board.presentation.web;

import lombok.RequiredArgsConstructor;
import org.prac.board.application.dto.BoardItemListResDTO;
import org.prac.board.application.service.BoardService;
import org.prac.board.domain.model.Board;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    /**
     * 전체 게시글 목록 조회 API
     */
    @GetMapping("/api/board/list/{page}")
    public List<BoardItemListResDTO> getBoardItemList(@PathVariable int page) throws Exception {
        return boardService.getBoardItemList(page);
    }
}
