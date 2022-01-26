package com.ex.memberboard2.controller;

import com.ex.memberboard2.dto.CommentDetailDTO;
import com.ex.memberboard2.dto.CommentSaveDTO;
import com.ex.memberboard2.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService cs;
    @PostMapping("/save")
    public @ResponseBody List<CommentDetailDTO> save(@ModelAttribute CommentSaveDTO commentSaveDTO){
        cs.save(commentSaveDTO); // 댓글저장
        List<CommentDetailDTO> commentList = cs.findAll(commentSaveDTO.getBoardId()); // 해당 게시글의 댓글목록 불러오기
        System.out.println("commentList = " + commentList);
        return commentList;

    }
}
