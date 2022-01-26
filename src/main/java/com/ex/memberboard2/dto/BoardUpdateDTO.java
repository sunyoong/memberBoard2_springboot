package com.ex.memberboard2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardUpdateDTO {
    private Long boardId; // 글번호
    private String memberEmail; // 글쓴이(=memberEmail)
    private String boardTitle; // 제목
    private String boardContents; // 내용
    private MultipartFile boardFile; // 파일
    private String boardFileName; // 파일명


    public BoardUpdateDTO(Long boardId, String boardTitle, String boardContents, MultipartFile boardFile, String boardFileName) {
        this.boardId = boardId;
        this.boardTitle = boardTitle;
        this.boardContents = boardContents;
        this.boardFile = boardFile;
        this.boardFileName = boardFileName;
    }
}
