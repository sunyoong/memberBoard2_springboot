package com.ex.memberboard2.service;

import com.ex.memberboard2.dto.CommentDetailDTO;
import com.ex.memberboard2.dto.CommentSaveDTO;

import java.util.List;

public interface CommentService {
    void save(CommentSaveDTO commentSaveDTO);

    List<CommentDetailDTO> findAll(Long boardId);
}
