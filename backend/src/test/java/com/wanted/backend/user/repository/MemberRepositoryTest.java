package com.wanted.backend.user.repository;

import com.wanted.backend.user.entity.Authority;
import jakarta.transaction.Transactional;
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

	@Autowired
	AuthorityRepository authorityRepository;
	
	Member savedMember;
	
	@BeforeEach
	public void saveDefaultMember() {
		Member member = new Member("22cun2@naver.com","1234");
		Authority authority1 = Authority.builder().name("ADMIN").build();
		Authority authority2 = Authority.builder().name("USER").build();
		authorityRepository.save(authority1);
		authorityRepository.save(authority2);
		member.addAuthority(authority1);
		member.addAuthority(authority2);
		memberRepository.save(member);
	}
	
	@Test
	public void save() {
		Member member = new Member("22cun3@naver.com","1234");
		memberRepository.save(member);
		Member savedMember = memberRepository.findById(member.getPassword()).orElseThrow();

	}
	
	@Test
	@Transactional
	public void findByEmail() {
		String email = "22cun2@naver.com";
		Member savedMember = memberRepository.findById(email).orElseThrow();
		Assertions.assertThat(savedMember.getEmail().equals(email));
		Assertions.assertThat(savedMember.getAuthorityList().size() == 2);
	}
	
	
}
