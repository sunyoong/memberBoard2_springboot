package com.ex.memberboard2.controller;

import com.ex.memberboard2.dto.MemberDetailDTO;
import com.ex.memberboard2.dto.MemberLoginDTO;
import com.ex.memberboard2.dto.MemberSaveDTO;
import com.ex.memberboard2.dto.MemberUpdateDTO;
import com.ex.memberboard2.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService ms;

    // 회원가입 창으로 이동
    @GetMapping("/save")
    public String saveform(Model model){
        model.addAttribute("member", new MemberSaveDTO());
        return "member/save";
    }

    // 회원가입 처리
    @PostMapping("/save")
    public String save(@Validated @ModelAttribute("member") MemberSaveDTO memberSaveDTO, BindingResult bindingResult, Model model) throws IOException {
        if(bindingResult.hasErrors()){
            return "member/save";
        }
        Long memberId = ms.save(memberSaveDTO);

        return "member/index";
    }


    // 이메일체크
    @PostMapping("/emailCheck")
    public @ResponseBody String email(@RequestParam("memberEmail") String memberEmail){
        String result = "";
        System.out.println("controller에서 email부분");
        String emailResult = ms.findByMemberEmail(memberEmail);
        System.out.println("controller에서 emailResult : " +  emailResult);
        if(emailResult=="ok"){
            result = "ok";
        }

        System.out.println("MemberController.email에서 eamilResult" + emailResult);
        return result;
    }

    // 회원목록 조회
    @GetMapping("/")
    public String findAll(@ModelAttribute MemberDetailDTO memberDetailDTO, Model model){
        List<MemberDetailDTO> memberList = ms.findAll();
        model.addAttribute("memberList", memberList);
        return "member/findAll";
    }

//    // 회원상세조회
//    @GetMapping("/detail/{memberId}")
//    public String findById(@PathVariable("memberId") Long memberId, Model model){
//        MemberDetailDTO memberDetailDTO = ms.findById(memberId);
//        model.addAttribute("member", memberDetailDTO);
//        return "member/detail";
//    }

    // 로그인 화면요청
    @GetMapping("/login")
    public String loginform(Model model){
        model.addAttribute("login", new MemberLoginDTO());
        return "member/login";
    }

    // 로그인처리
    @PostMapping("/login")
    public String login(@Validated @ModelAttribute("login") MemberLoginDTO memberLoginDTO, BindingResult bindingResult,  HttpSession session){
        if(bindingResult.hasErrors()) {
            return "member/login";
        }



        boolean loginResult = ms.login(memberLoginDTO);
        System.out.println("loginResult = " + loginResult);

        if(loginResult==true){
            session.setAttribute("loginEmail", memberLoginDTO.getLoginEmail());
            session.setAttribute("loginId", memberLoginDTO.getLoginId());
            System.out.println("loginId = " + memberLoginDTO.getLoginId());

            return "index";

        } else {

            bindingResult.reject("loginFail", "이메일 또는 비밀번호가 틀립니다.");
            return "member/login";
        }
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "index";
    }

    // 관리자페이지
    @GetMapping("/admin")
    public String admin(){
        return "member/admin";
    }








    // 정보삭제
    @GetMapping("/delete/{memberId}")
    public String delete(@PathVariable("memberId") Long memberId){
        ms.delete(memberId);
        return "redirect:/member/findAll";
    }


    // 마이페이지
    @GetMapping("/mypage")
    public String mypageform(){
        return "member/mypage";
    }


    // 회원정보 상세조회
    @GetMapping("/{loginId}")
    public String findById(@PathVariable("loginId") Long memberId, Model model){
        MemberDetailDTO memberDetailDTO = ms.findById(memberId);
        model.addAttribute("member", memberDetailDTO);
        return "member/detail";

    }


    // 정보수정 화면요청
    @GetMapping("/update/{memberId}")
    public String updateform(@PathVariable("memberId")Long memberId, Model model){
        MemberDetailDTO memberDetailDTO = ms.findById(memberId);
        model.addAttribute("member", memberDetailDTO);
        return "member/update";
    }




    // 정보수정처리(ajax)
    @PutMapping("/{memberId}")
    public ResponseEntity update2(@RequestBody MemberUpdateDTO memberUpdateDTO) throws IOException {
        ms.update(memberUpdateDTO);
        return new ResponseEntity(HttpStatus.OK);
    }



}
