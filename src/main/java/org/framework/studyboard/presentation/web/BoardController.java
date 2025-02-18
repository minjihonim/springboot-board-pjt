package org.framework.studyboard.presentation.web;

import lombok.RequiredArgsConstructor;
import org.framework.studyboard.application.dto.BoardItemListResDTO;
import org.framework.studyboard.application.dto.BoardWritingReqDTO;
import org.framework.studyboard.application.dto.BoardWritingResDTO;
import org.springframework.web.bind.annotation.*;
import org.framework.studyboard.application.service.BoardService;
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

    /**
     * 게시글 작성 API
     */
    @PostMapping("/api/board/writing")
    public BoardWritingResDTO insertBoardWriting(@RequestParam BoardWritingReqDTO param) throws Exception {
        BoardWritingResDTO result = new BoardWritingResDTO();
        // 파라미터 validate
        BoardWritingResDTO validResult = validateParam(param, result);
        if(validResult != null) {
            return validResult;
        }

        return boardService.insertBoardWriting(param);
    }

    private BoardWritingResDTO validateParam(BoardWritingReqDTO param, BoardWritingResDTO result) {
        if(param.getPassword().length() < 4) {
            result.setCode(9999);
            result.setMessage("비밀번호는 4자리 이상 입력해야 합니다.");
            return result;
        }
        return null;
    }
}
