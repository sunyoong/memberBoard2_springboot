package com.ex.memberboard2.service;



import com.ex.memberboard2.dto.MemberDetailDTO;
import com.ex.memberboard2.dto.MemberLoginDTO;
import com.ex.memberboard2.dto.MemberSaveDTO;
import com.ex.memberboard2.dto.MemberUpdateDTO;
import com.ex.memberboard2.entity.MemberEntity;
import com.ex.memberboard2.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    private final MemberRepository mr;
    @Override
    public Long save(MemberSaveDTO memberSaveDTO) throws IOException {
        if(memberSaveDTO.getMemberFile()!=null) {
            // 파일첨부 기능
            MultipartFile m_file = memberSaveDTO.getMemberFile(); // db에 저장된 파일
            String m_fileName = m_file.getOriginalFilename(); // 파일이름을 db에 저장하기 위해 m_file에서 이름을 가져옴
            m_fileName = System.currentTimeMillis() + "-" + m_fileName; // 파일이름 구분하기 위해 현재시간을 숫자로 나타낸것? 을 앞에 추가함
            System.out.println("m_fileName = " + m_fileName);

            String savePath = "C:\\development_psy\\source\\springboot\\MemberBoard\\src\\main\\resources\\static\\upload\\" + m_fileName;
            //  => 파일 저장경로\\ + m_fileName

            if (!m_file.isEmpty()) {
                m_file.transferTo(new File(savePath));
                // 파일이 비어있지 않으면 savePath에 저장하겠다
            }
            memberSaveDTO.setMemberFileName(m_fileName);
            //dto에 파일이름 담음

            // dto-> entity 변환
        }
        MemberEntity memberEntity = MemberEntity.saveMember(memberSaveDTO);
        return mr.save(memberEntity).getId();

    }

    // 이메일 중복확인
    @Override
    public String findByMemberEmail(String memberEmail) {

        MemberEntity memberEntity = mr.findByMemberEmail(memberEmail);
        System.out.println("serviceImple에서 memberEmailEntity=" + memberEntity);
        if(memberEntity==null){
            // 만약 db가 비어있으면 가입할 수 있음.
            return "ok";
        } else{
            return "no";
        }

    }

    //
    @Override
    public List<MemberDetailDTO> findAll() {
        // entityList -> dtoList로 변환
        List<MemberEntity> memberEntityList = mr.findAll();
        List<MemberDetailDTO> memberList = new ArrayList<>();
        for(MemberEntity m : memberEntityList){
            memberList.add(MemberDetailDTO.toMemberDetailDTO(m));
        }

        System.out.println("memberList = " + memberList);
        return memberList;
    }

    @Override
    public MemberDetailDTO findById(Long memberId) {
        MemberEntity memberEntity = mr.findById(memberId).get();
        MemberDetailDTO memberDetailDTO = MemberDetailDTO.toMemberDetailDTO(memberEntity);
        return memberDetailDTO;
    }

    @Override
    public boolean login(MemberLoginDTO memberLoginDTO) {
        MemberEntity memberEntity = mr.findByMemberEmail(memberLoginDTO.getLoginEmail());
        MemberDetailDTO memberDetailDTO = MemberDetailDTO.toMemberDetailDTO(memberEntity);
        System.out.println("memberEntity = " + memberEntity.getMemberPassword());
        System.out.println("memberDetailDTO = " + memberDetailDTO.getMemberPassword());
        System.out.println("memberLoginDTO = " + memberLoginDTO.getLoginPassword());
        if(memberDetailDTO!=null){
            // 여기서 굳이 entity를 dto타입으로 변환할 필요는 없ㅇ음.
            // 그냥 ~entity.get~~()으로 찍으면 그 값이 나옴!
            if(memberDetailDTO.getMemberPassword().equals(memberLoginDTO.getLoginPassword())){
                memberLoginDTO.setLoginId(memberDetailDTO.getMemberId());
                System.out.println("memberDetailDTO.getMemberId() = " + memberDetailDTO.getMemberId());

                return true;
            } else{
                return false;
            }
        } else{
            return false;
        }
    }

    @Override
    public void delete(Long memberId) {
        mr.deleteById(memberId);


    }


    // 정보수정(ajax)
    @Override
    public void update(MemberUpdateDTO memberUpdateDTO) throws IOException {
        if(memberUpdateDTO.getMemberUpdateFile()!=null) {
            // 파일첨부 기능
            MultipartFile m_file = memberUpdateDTO.getMemberUpdateFile(); // db에 저장된 파일
            String m_fileName = m_file.getOriginalFilename(); // 파일이름을 db에 저장하기 위해 m_file에서 이름을 가져옴
            m_fileName = System.currentTimeMillis() + "-" + m_fileName; // 파일이름 구분하기 위해 현재시간을 숫자로 나타낸것? 을 앞에 추가함
            System.out.println("m_fileName = " + m_fileName);

            String savePath = "C:\\development_psy\\source\\springboot\\MemberBoard2\\src\\main\\resources\\static\\member\\upload\\" + m_fileName;
            //  => 파일 저장경로\\ + m_fileName

            if (!m_file.isEmpty()) {
                m_file.transferTo(new File(savePath));
                // 파일이 비어있지 않으면 savePath에 저장하겠다
            }
            memberUpdateDTO.setMemberUpdateFileName(m_fileName);
            //dto에 파일이름 담음

            // dto-> entity 변환
        }
        MemberEntity memberEntity = MemberEntity.updateMember(memberUpdateDTO);
        //update할떄는 save로 하기
       mr.save(memberEntity);



    }
}
