package com.ex.memberboard2.service;


import com.ex.memberboard2.dto.MemberDetailDTO;
import com.ex.memberboard2.dto.MemberLoginDTO;
import com.ex.memberboard2.dto.MemberSaveDTO;
import com.ex.memberboard2.dto.MemberUpdateDTO;

import java.io.IOException;
import java.util.List;

public interface MemberService {
    Long save(MemberSaveDTO memberSaveDTO) throws IOException;

    String findByMemberEmail(String memberEmail);

    List<MemberDetailDTO> findAll();

    MemberDetailDTO findById(Long memberId);

    boolean login(MemberLoginDTO memberLoginDTO);

    void delete(Long memberId);

    void update(MemberUpdateDTO memberUpdateDTO) throws IOException;
}
