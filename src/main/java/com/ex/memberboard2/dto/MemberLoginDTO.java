package com.ex.memberboard2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberLoginDTO {
    private Long loginId;
    @NotNull(message = "아이디를 입력하세요")
    private String loginEmail;
    @NotNull(message = "비밀번호를 입력하세요")
    @Length(min=2, max=10, message="2~10자로 입력해주세요")
    private String loginPassword;
}
