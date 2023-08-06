package com.wanted.backend.user.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wanted.backend.user.entity.Member;

@SpringBootTest
public class MemberRepositoryTest {

	@Autowired
	MemberRepository memberRepository;
	
	Member savedMember;
	
	@BeforeEach
	public void saveDefaultMember() {
		Member member = new Member("22cun2@naver.com","1234");
		memberRepository.save(member);
	}
	
	@Test
	public void save() {
		Member member = new Member("22cun2@naver.com","1234");
		memberRepository.save(member);
		Member savedMember = memberRepository.getById(member.getPassword());
	}
	
	@Test
	public void findByEmail() {
		String email = "22cun2@naver.com";
		Member savedMember = memberRepository.getById(email);
		Assertions.assertThat(savedMember.getEmail().equals(email));
	}
	
	
}
