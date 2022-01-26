package com.ex.memberboard2.service;

import com.ex.memberboard2.common.PagingConst;
import com.ex.memberboard2.dto.*;
import com.ex.memberboard2.entity.BoardEntity;
import com.ex.memberboard2.entity.MemberEntity;
import com.ex.memberboard2.repository.BoardRepository;
import com.ex.memberboard2.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository br;
    private final MemberRepository mr;


    // 글 저장
    @Override
    public Long save(BoardSaveDTO boardSaveDTO) throws IOException {
        if(boardSaveDTO.getBoardFile()!=null){
            // 파일첨부 처리하기
        MultipartFile b_file = boardSaveDTO.getBoardFile();
        String b_fileName = System.currentTimeMillis() + b_file.getOriginalFilename();

        // 저장경로
            String savePath = "C:\\development_psy\\source\\springboot\\MemberBoard2\\src\\main\\resources\\static\\board\\upload\\" + b_fileName;
            // 만약 b_file이 비어있지 않으면 저장경로에 저장하기
           if(!b_file.isEmpty()){
               b_file.transferTo(new File(savePath));
           }
           // 파일이름 dto에 저장
            boardSaveDTO.setBoardFileName(b_fileName);

        }
        // dto -> entity(entity 클래스에서)
        MemberEntity memberEntity = mr.findByMemberEmail(boardSaveDTO.getMemberEmail());

        BoardEntity boardEntity = BoardEntity.saveBoard(boardSaveDTO, memberEntity);
        System.out.println("boardEntity = " + boardEntity);
        br.save(boardEntity);
        System.out.println("br.save(boardEntity) = " + br.save(boardEntity));
       return br.save(boardEntity).getId();

    }


// 페이징 처리된 글목록
    @Override
    public Page<BoardPagingDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber();
        page = (page==1) ? 0:(page-1);
        Page<BoardEntity> boardEntity = br.findAll(PageRequest.of(page, PagingConst.PAGE_LIMIT, Sort.by(Sort.Direction.DESC, "id")));
        Page<BoardPagingDTO> boardList = boardEntity.map(
                board -> new BoardPagingDTO(board.getId(), board.getMemberEmail(), board.getBoardTitle(), board.getBoardHits(),board.getCreateTime())
        );

        System.out.println("boardList = " + boardList);
        // dto형식으로 바꾼 boardList 리턴하기
        return boardList;

    }

    // 게시글 상세조회
    @Override
    public BoardDetailDTO findById(Long boardId) {
        // entity -> dto
        Optional<BoardEntity> optionalBoardEntity = br.findById(boardId);
        BoardDetailDTO boardDetailDTO = null;
        if(optionalBoardEntity.isPresent()){
            BoardEntity boardEntity = optionalBoardEntity.get();
             boardDetailDTO = BoardDetailDTO.toBoardDetail(boardEntity);
        }

        return boardDetailDTO;
    }

    // 수정처리
    @Override
    public void update(BoardUpdateDTO boardUpdateDTO) {
        br.save(BoardEntity.updateBoard(boardUpdateDTO));
    }


}
