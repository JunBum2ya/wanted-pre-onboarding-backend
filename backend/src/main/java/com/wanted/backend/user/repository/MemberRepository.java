package com.wanted.backend.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wanted.backend.user.entity.Member;

public interface MemberRepository extends JpaRepository<Member,String>,MemberRepositoryCustom {

}
