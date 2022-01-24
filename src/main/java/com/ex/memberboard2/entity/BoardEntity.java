package com.ex.memberboard2.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name="board_table")
public class BoardEntity extends com.ex.memberboard2.entity.BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="board_id")
    private Long id;

    @Column
    private String boardTitle; // 글제목
    @Column
    private String boardWriter; // 작성자(memberEmail)
    @Column
    private String boardContents; // 글내용
    @Column
    private String boardHits; // 조회수
    @Column
    private String boardFileName; // 파일명
    @Column
    private String crateTime; // 작성날짜
/*    @Column
    private String updateTime; // 수정날짜*/

    // member_table과 참조관계를 맺어야 합니다.
    // 게시글:회원 = n:1 (다대일 관계)
//    @ManyToOne(fetch=FetchType.LAZY) // 어떤 관계인지. LAZY(지연로딩)는 그때그때 필요한 걸 가져오겠다.
//    @JoinColumn(name="member_id") // 참조할 컬럼
//    private MemberEntity memberEntity; // 회원 ENTITY를 가져옴




}
