package com.ex.memberboard2.dto;


import com.ex.memberboard2.entity.CommentEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDetailDTO {
    private Long commentId;
    private Long boardId;
    private String CommentWriter;
    private String CommentContents;
    private LocalDateTime createTime;

    public static CommentDetailDTO CommentDetailDTO(CommentEntity commentEntity) {
        CommentDetailDTO commentDetailDTO = new CommentDetailDTO();
        commentDetailDTO.setCommentId(commentEntity.getId());
        commentDetailDTO.setBoardId(commentEntity.getBoardEntity().getId());
        commentDetailDTO.setCommentWriter(commentEntity.getCommentWriter());
        commentDetailDTO.setCommentContents(commentEntity.getCommentContents());
        commentDetailDTO.setCreateTime(commentEntity.getCreateTime());
        return commentDetailDTO;
    }
}
