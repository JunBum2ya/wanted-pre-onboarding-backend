package com.wanted.backend.user.repository;

import com.wanted.backend.user.entity.Authority;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wanted.backend.user.entity.Member;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class MemberRepositoryTest {

	@Autowired
	MemberRepository memberRepository;

	@Autowired
	AuthorityRepository authorityRepository;
	
	Member savedMember;
	
	@BeforeEach
	public void saveDefaultMember() {
		Authority authority1 = Authority.builder().name("ADMIN").build();
		Authority authority2 = Authority.builder().name("USER").build();
		authorityRepository.save(authority1);
		authorityRepository.save(authority2);
		List<Authority> authorityList = Arrays.asList(authority1,authority2);
		Member member = Member
				.builder()
				.email("22cun2@naver.com")
				.password("12345678")
				.authorityList(authorityList)
				.build();
		memberRepository.save(member);
	}
	
	@Test
	public void save() {
		Member member = Member.builder().email("22cun3@naver.com").password("1234").build();
		memberRepository.save(member);
		Member savedMember = memberRepository.findById(member.getEmail()).orElseThrow();
		Assertions.assertThat(savedMember.getEmail().equals("22cun3@naver.com"));
	}
	
	@Test
	@Transactional
	public void findByEmail() {
		String email = "22cun2@naver.com";
		Member savedMember = memberRepository.findByEmailWithAuthority(email).orElseThrow();
		Assertions.assertThat(savedMember.getEmail().equals(email));
		Assertions.assertThat(savedMember.getAuthorityList().size() == 2);
	}
	
	
}
