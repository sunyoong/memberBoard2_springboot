package com.ex.memberboard2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class MemberUpdateDTO {
    private Long memberId;
    private String memberEmail;
    private String memberPassword;
    private String memberName;
    private MultipartFile memberUpdateFile;
    private String memberUpdateFileName;
    private LocalDateTime updateDate;

    // 작성정보 저장

//
//    public MemberUpdateDTO(Long memberId, String memberEmail, String memberPassword, String memberName, String memberFileName) {
//        this.memberId = memberId;
//        this.memberEmail = memberEmail;
//        this.memberPassword = memberPassword;
//        this.memberName = memberName;
//        this.memberFileName = memberFileName;
//    }
}

