package com.cgv.CgvProject.repositroty;

import com.cgv.CgvProject.domain.Member;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JapMemberRepository implements MemberRepository {

    private final EntityManager em;

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Member findById(Long id) {
        return em.find(Member.class, id);
    }

    @Override
    public Member findByUserId(String userId) {
        List<Member> result = em.createQuery("Select m from Member m where m.userId = :userId", Member.class)
                .setParameter("userId", userId)
                .getResultList();

        return result.isEmpty() ? null : result.get(0); // 결과가 없으면 null 반환
    }

    @Override
    public void delete(Member member) {
        em.remove(member);
    }

}
