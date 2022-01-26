package com.ex.memberboard2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardSaveDTO {
//    private Long boardId; // 글번호
    private String memberEmail; // 글쓴이(=memberEmail)
    private String boardTitle; // 제목
    private String boardContents; // 내용
    private MultipartFile boardFile; // 파일
    private String boardFileName; // 파일명


}
