package com.ex.memberboard2.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentSaveDTO {
    private Long boardId;
    private String CommentWriter;
    private String CommentContents;
}
