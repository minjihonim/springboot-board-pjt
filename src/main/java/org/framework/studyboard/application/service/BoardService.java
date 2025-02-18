package org.framework.studyboard.application.service;

import lombok.RequiredArgsConstructor;
import org.framework.studyboard.application.dto.BoardItemListResDTO;
import org.framework.studyboard.application.dto.BoardWritingReqDTO;
import org.framework.studyboard.application.dto.BoardWritingResDTO;
import org.framework.studyboard.domain.model.Board;
import org.framework.studyboard.global.common.ErrorCode;
import org.framework.studyboard.infrastructure.persistence.BoardRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final ModelMapper modelMapper;

    /**
     * 전체 게시글 목록 조회
     * @param page
     * @return
     * @throws Exception
     */
    public List<BoardItemListResDTO> getBoardItemList(int page) throws Exception {
        // 기본 사이즈
        int size = 20;

        Sort sort = Sort.by("createdDt").descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        // 작성날짜 내림차순 조회
        Page<Board> boardList = boardRepository.findAll(pageable);

        return boardList.stream()
                .map(data -> modelMapper.map(data, BoardItemListResDTO.class))
                .collect(Collectors.toList());
    }

    @Transactional
    public BoardWritingResDTO insertBoardWriting(BoardWritingReqDTO param) throws Exception {
        // 리턴 객체
        BoardWritingResDTO result = new BoardWritingResDTO();

        // 비밀번호 암호화
        encPassword(param);

        Board board = Board.builder()
                .subject(param.getSubject())
                .content(param.getContent())
                .password(param.getPassword())
                .writer(param.getWriter()).build();

        // 게시글 등록
        Board affectedRows = boardRepository.save(board);
        if(affectedRows.getId() != null) {
            result.setCode(ErrorCode.SUCCESS.getCode());
            result.setMessage(ErrorCode.SUCCESS.getMessage());
        }

        return result;
    }

    private static void encPassword(BoardWritingReqDTO param) throws NoSuchAlgorithmException {
        // 비밀빈호 hash 암호화
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(param.getPassword().getBytes(StandardCharsets.UTF_8));

        // 바이트 배열을 16진수 문자열로 변환
        String hashPassword = HexFormat.of().formatHex(hash);
        param.setPassword(hashPassword);
    }
}
