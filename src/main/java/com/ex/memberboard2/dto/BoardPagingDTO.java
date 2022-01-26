package com.ex.memberboard2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardPagingDTO {
    private Long boardId;
    private String memberEmail;
    private String boardTitle;
    private Long boardHits;
    private LocalDateTime boardSaveDate;

}

