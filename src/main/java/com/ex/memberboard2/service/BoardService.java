package com.ex.memberboard2.service;

import com.ex.memberboard2.dto.BoardDetailDTO;
import com.ex.memberboard2.dto.BoardPagingDTO;
import com.ex.memberboard2.dto.BoardSaveDTO;
import com.ex.memberboard2.dto.BoardUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;


public interface BoardService {
    Long save(BoardSaveDTO boardSaveDTO) throws IOException;

    Page<BoardPagingDTO> paging(Pageable pageable);

    BoardDetailDTO findById(Long boardId);

    void update(BoardUpdateDTO boardUpdateDTO);
}
