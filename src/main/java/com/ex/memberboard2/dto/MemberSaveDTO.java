package com.ex.memberboard2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberSaveDTO {
    @NotBlank(message = "필수 입력사항입니다.")
    private String memberEmail;
    @NotBlank(message = "필수 입력사항입니다.")
    @Length(min=2, max=10, message="2~10자 이내로 입력해주세요.")
    private String memberPassword;
    @NotBlank(message = "필수 입력사항입니다.")
    private String memberName;
    private MultipartFile memberFile; // jsp에서 컨트롤러로 넘어올 때 파일 자체를 담는 필드(db말고 별도의 경로에 담아줘야함)
    private String memberFileName; // db에 파일이름 저장하는 필드

}







