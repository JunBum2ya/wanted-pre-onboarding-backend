package com.wanted.backend.user.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wanted.backend.user.entity.Member;
import com.wanted.backend.user.entity.QAuthority;
import com.wanted.backend.user.entity.QMember;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MemberRepositoryImpl implements MemberRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    public MemberRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public Optional<Member> findByEmailWithAuthority(String email) {
        return Optional.ofNullable(jpaQueryFactory
                .selectFrom(QMember.member)
                .leftJoin(QMember.member.authorityList,QAuthority.authority)
                .fetchFirst());
    }
}
