package com.ex.memberboard2.entity;


import com.ex.memberboard2.dto.CommentSaveDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Getter
@Setter
@Table(name="comment_table")
public class CommentEntity  extends BaseEntity {
    // 댓글번호, 작성자, 내용, 원글(게시글번호), 작성시간
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="comment_id")
    private Long id;

    // 댓글 : 게시글 => n:1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="board_id")// board테이블 pk 컬럼이름
    private BoardEntity boardEntity;

    // 댓글 : 회원 => n:1
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private MemberEntity memberEntity;

    @Column
    private String commentWriter;

    @Column
    private String CommentContents;


    public static CommentEntity SaveEntity(CommentSaveDTO commentSaveDTO, BoardEntity boardEntity, MemberEntity memberEntity) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setId(commentSaveDTO.getBoardId());
        commentEntity.setCommentWriter(commentSaveDTO.getCommentWriter());
        commentEntity.setCommentContents(commentSaveDTO.getCommentContents());
        commentEntity.setBoardEntity(boardEntity);
        commentEntity.setMemberEntity(memberEntity);
        return commentEntity;
    }
}
