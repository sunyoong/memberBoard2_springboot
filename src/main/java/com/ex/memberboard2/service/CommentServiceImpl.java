package com.ex.memberboard2.service;

import com.ex.memberboard2.dto.CommentDetailDTO;
import com.ex.memberboard2.dto.CommentSaveDTO;
import com.ex.memberboard2.entity.BoardEntity;
import com.ex.memberboard2.entity.CommentEntity;
import com.ex.memberboard2.entity.MemberEntity;
import com.ex.memberboard2.repository.BoardRepository;
import com.ex.memberboard2.repository.CommentRepository;
import com.ex.memberboard2.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{
    private final CommentRepository cr;
    private final BoardRepository br;
    private final MemberRepository mr;
    @Override
    public void save(CommentSaveDTO commentSaveDTO) {
        BoardEntity boardEntity = br.findById(commentSaveDTO.getBoardId()).get();
        System.out.println("serviceImpl에서 boardEntity = " + boardEntity);
        MemberEntity memberEntity = mr.findByMemberEmail(commentSaveDTO.getCommentWriter());
        CommentEntity commentEntity = CommentEntity.SaveEntity(commentSaveDTO, boardEntity, memberEntity); // 댓글 : 게시판, 멤버 참조

    }

    @Override
    public List<CommentDetailDTO> findAll(Long boardId) {
        BoardEntity boardEntity = br.findById(boardId).get();
        System.out.println("boardEntity = " + boardEntity);
        List<CommentEntity> commentEntityList = boardEntity.getCommentEntityList();
        System.out.println("commentEntityList = " + commentEntityList);
        // entity -> dto로 바꾸기
        List<CommentDetailDTO> commentList = new ArrayList<>();
        for(CommentEntity c : commentEntityList){
            CommentDetailDTO commentDetailDTO = CommentDetailDTO.CommentDetailDTO(c);
            commentList.add(commentDetailDTO);
        }
        return commentList;
    }

}
