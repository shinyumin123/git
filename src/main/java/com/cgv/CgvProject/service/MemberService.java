package com.cgv.CgvProject.service;

import com.cgv.CgvProject.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cgv.CgvProject.repositroty.MemberRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final JwtUtility jwtUtility;

    @Transactional
    public Member tokenToMember(String token) {
        return memberRepository.findByUserId(jwtUtility.validateToken(token).getSubject());
    }

    @Transactional
    public Member signup(String userId, String password, String name,String number) {
        Member member = memberRepository.findByUserId(userId);
        if (member != null) return null;
        return memberRepository.save(new Member(userId, password, name, number));
    }

    @Transactional
    public String login(String userId, String password) {
        Member member = memberRepository.findByUserId(userId);
        if (member != null &&  member.checkPassword(password)) {
            return jwtUtility.generateToken(member.getUserId());
        }
        return null;
    }

    public boolean deleteMember(String token) {
        Member member = tokenToMember(token);
        if(member == null) return false;
        memberRepository.delete(member);
        return true;
    }

    public Member findByUserId(String userId) {
        Member member = memberRepository.findByUserId(userId);
        if(member == null) return null;
        return member;
    }



}
