package com.cgv.CgvProject.controllor;

import com.cgv.CgvProject.DTO.MemberDTO;
import com.cgv.CgvProject.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cgv.CgvProject.service.MemberService;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/member/signup")
    public ResponseEntity<?> signup(@RequestBody MemberDTO.MemberCreateRequest request) {
        Member member = memberService.signup(request.getUserId(), request.getPassword(), request.getName(), request.getNumber());
        if (member == null) return ResponseEntity.ok("이미 존재");
        String token = memberService.login(request.getUserId(), request.getPassword());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/login")
    public String login(@RequestBody MemberDTO.MemberLoginRequest request) {
        return memberService.login(request.getUserId(), request.getPassword());
    }

    @GetMapping("/member/{userId}")
    public MemberDTO.MemberResponse getMember(@PathVariable String userId) {
        Member member = memberService.findByUserId(userId);
        MemberDTO.MemberResponse response = new MemberDTO.MemberResponse(member.getUserId(), member.getName());
        return response;
    }

    @DeleteMapping("/member")
    public void delete(@RequestBody MemberDTO.MemberDeleteRequest request) {
        memberService.deleteMember(request.getToken());
    }


}
