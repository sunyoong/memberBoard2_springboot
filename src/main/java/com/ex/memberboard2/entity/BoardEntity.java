package com.ex.memberboard2.entity;

import com.ex.memberboard2.dto.BoardSaveDTO;
import com.ex.memberboard2.dto.BoardUpdateDTO;
import com.ex.memberboard2.dto.MemberSaveDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Table(name="board_table")
public class BoardEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="board_id")
    private Long id;
    @Column
    private String memberEmail; // 작성자(memberEmail)
    @Column
    private String boardTitle; // 글제목
    @Column
    private String boardContents; // 글내용
    @Column
    private Long boardHits; // 조회수
    @Column
    private String boardFileName; // 파일명
    @Column
    private LocalDateTime createTime; // 작성날짜
    @Column
    private LocalDateTime updateTime; // 수정날짜

    // member_table과 참조관계를 맺어야 합니다.
    // 게시글:회원 = n:1 (다대일 관계)
    @ManyToOne(fetch=FetchType.LAZY) // 어떤 관계인지. LAZY(지연로딩)는 그때그때 필요한 걸 가져오겠다.
    @JoinColumn(name="member_id") // 참조할 컬럼
    private MemberEntity memberEntity; // 회원 ENTITY를 가져옴

    // 게시글 : 댓글 = 1:n
    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CommentEntity> commentEntityList = new ArrayList<>();


    // 글 저장
    public static BoardEntity saveBoard(BoardSaveDTO boardSaveDTO, MemberEntity memberEntity){
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setMemberEmail(boardSaveDTO.getMemberEmail());
        boardEntity.setBoardTitle(boardSaveDTO.getBoardTitle());
        boardEntity.setBoardContents(boardSaveDTO.getBoardContents());
        boardEntity.setBoardFileName(boardSaveDTO.getBoardFileName());
        boardEntity.setMemberEntity(memberEntity);
        return boardEntity;
    }

    // 수정정보 저장
    public static BoardEntity updateBoard(BoardUpdateDTO boardUpdateDTO){
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setId(boardUpdateDTO.getBoardId());
        boardEntity.setBoardTitle(boardUpdateDTO.getBoardTitle());
        boardEntity.setBoardContents(boardUpdateDTO.getBoardContents());
        boardEntity.setBoardFileName(boardUpdateDTO.getBoardFileName());
        boardEntity.setUpdateTime(LocalDateTime.now());
        return boardEntity;

    }


}
