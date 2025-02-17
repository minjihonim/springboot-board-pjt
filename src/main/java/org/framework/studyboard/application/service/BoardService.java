package org.framework.studyboard.application.service;

import lombok.RequiredArgsConstructor;
import org.framework.studyboard.application.dto.BoardItemListResDTO;
import org.framework.studyboard.domain.model.Board;
import org.framework.studyboard.infrastructure.persistence.BoardRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
}
