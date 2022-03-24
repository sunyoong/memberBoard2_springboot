package com.ex.memberboard2.controller;

import com.ex.memberboard2.common.PagingConst;
import com.ex.memberboard2.dto.BoardDetailDTO;
import com.ex.memberboard2.dto.BoardPagingDTO;
import com.ex.memberboard2.dto.BoardSaveDTO;
import com.ex.memberboard2.dto.BoardUpdateDTO;
import com.ex.memberboard2.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    // 서비스클래스 주입
    private final BoardService bs;

    // 글작성 화면요청
    @GetMapping("/save")
    public String saveForm(Model model){
        model.addAttribute("board", new BoardSaveDTO());
        return "board/save";
    }

    // 글작성 (글목록 화면으로 돌아감)
    @PostMapping("/save")
    public String save(@ModelAttribute BoardSaveDTO boardSaveDTO, Model model, @PageableDefault(page=1) Pageable pageable) throws IOException {
        Long boardId =  bs.save(boardSaveDTO);
        System.out.println("boardId = " + boardId);
        // 페이징처리된 목록
        return "redirect:/board";

    }

    // 페이징처리 된 글목록
    @GetMapping
    public String paging(@PageableDefault(page=1) Pageable pageable, Model model){
        Page<BoardPagingDTO> boardList = bs.paging(pageable);
        // bs.paging(pageable) => boardList
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber()/ PagingConst.BLOCK_LIMIT)))-1) * PagingConst.BLOCK_LIMIT;
        System.out.println("startPage = " + startPage);
        int endPage = ((startPage + PagingConst.BLOCK_LIMIT -1) < boardList.getTotalPages()) ? startPage + PagingConst.BLOCK_LIMIT-1  : boardList.getTotalPages();
        System.out.println("endPage = " + endPage);
        model.addAttribute("boardList", boardList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "board/paging";


    }

    // 게시글 상세조회
    @GetMapping("{boardId}")
    public String findById(@PathVariable("boardId") Long boardId, Model model){
        BoardDetailDTO boardDetailDTO = bs.findById(boardId);
        model.addAttribute("board", boardDetailDTO);
        return "board/detail";
    }

    // 게시글 수정(기존화면 요청)
    @GetMapping("/update/{boardId}")
    public String updateForm(@PathVariable("boardId") Long boardId, Model model){
        BoardDetailDTO boardDetailDTO = bs.findById(boardId);
        model.addAttribute("board", boardDetailDTO);
        return "board/update";
    }

    // ajax로 수정하기
    @PutMapping("/{boardId}")
    public ResponseEntity update(@RequestBody BoardUpdateDTO boardUpdateDTO){
        bs.update(boardUpdateDTO);
        return new ResponseEntity(boardUpdateDTO, HttpStatus.OK);
    }


//    @PostMapping("/{boardId}")
//    public String update(@ModelAttribute BoardUpdateDTO boardUpdateDTO){
//        bs.update(boardUpdateDTO);
//        return "redirect:/board/" + boardUpdateDTO.getBoardId();
//    }



}
