package com.wanted.backend.user.repository;

import com.wanted.backend.user.entity.Member;

import java.util.Optional;

public interface MemberRepositoryCustom {
    public Optional<Member> findByEmailWithAuthority(String email);
}
