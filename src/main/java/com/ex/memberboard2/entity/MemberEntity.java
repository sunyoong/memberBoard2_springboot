package com.ex.memberboard2.entity;

import com.ex.memberboard2.dto.MemberSaveDTO;
import com.ex.memberboard2.dto.MemberUpdateDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name="member_table")
public class MemberEntity extends com.ex.memberboard2.entity.BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id")
    private long id;

    @Column
    private String memberEmail;

    @Column
    private String memberPassword;

    @Column
    private String memberName;

    @Column
    private String memberFileName;

    @Column
    private LocalDateTime createTime;

    @Column
    private LocalDateTime updateTime;

    // 참조관계 설정(BOARD)
    // 회원:게시글 = 1:N(일대다 관계)
//    @OneToMany(mappedBy = "memberEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
//    private List<BoardEntity> boardEntityList = new ArrayList<>();


    public static MemberEntity saveMember(MemberSaveDTO memberSaveDTO){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberEmail(memberSaveDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberSaveDTO.getMemberPassword());
        memberEntity.setMemberName(memberSaveDTO.getMemberName());
        memberEntity.setMemberFileName(memberSaveDTO.getMemberFileName());
        memberEntity.setCreateTime(LocalDateTime.now());
        return memberEntity;
    }

    // 수정정보 저장
    public static MemberEntity updateMember(MemberUpdateDTO memberUpdateDTO){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setId(memberUpdateDTO.getMemberId());
        memberEntity.setMemberEmail(memberUpdateDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberUpdateDTO.getMemberPassword());
        memberEntity.setMemberName(memberUpdateDTO.getMemberName());
        memberEntity.setMemberFileName(memberUpdateDTO.getMemberUpdateFileName());
        memberEntity.setUpdateTime(LocalDateTime.now());
        return memberEntity;

    }


}
