package com.ex.memberboard2.dto;

import com.ex.memberboard2.entity.BoardEntity;
import com.ex.memberboard2.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class BoardDetailDTO {
    private Long boardId; // 글번호
    private String memberEmail; // 글쓴이(=memberEmail)
    private String boardTitle; // 제목
    private String boardContents; // 내용
    private Long boardHits; // 조회수
    private MultipartFile boardFile; // 파일
    private String boardFileName; // 파일명
    private LocalDateTime boardSaveDate; // 작성일



    // entity -> dto(상세조회할때)
    public static BoardDetailDTO toBoardDetail(BoardEntity boardEntity){
        BoardDetailDTO boardDetailDTO = new BoardDetailDTO();
         boardDetailDTO.setBoardId(boardEntity.getId());
         boardDetailDTO.setMemberEmail(boardEntity.getMemberEmail());
         boardDetailDTO.setBoardTitle(boardEntity.getBoardTitle());
         boardDetailDTO.setBoardContents(boardEntity.getBoardContents());
         boardDetailDTO.setBoardHits(boardEntity.getBoardHits());
         boardDetailDTO.setBoardFileName(boardEntity.getBoardFileName());
         boardDetailDTO.setBoardSaveDate(boardEntity.getCreateTime());
         return boardDetailDTO;

    }



}
