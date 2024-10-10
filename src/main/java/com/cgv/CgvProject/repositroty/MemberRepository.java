package com.cgv.CgvProject.repositroty;

import com.cgv.CgvProject.domain.Member;

import java.util.List;

public interface MemberRepository {

    Member save(Member member);

    Member findById(Long id);

    Member findByUserId(String userId);

    void delete(Member member);

}
