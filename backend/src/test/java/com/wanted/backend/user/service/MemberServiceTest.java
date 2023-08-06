package com.wanted.backend.user.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.wanted.backend.user.dto.MemberJoinRequest;
import com.wanted.backend.user.entity.Member;

@SpringBootTest
public class MemberServiceTest {
	
	MemberJoinRequest joinRequest;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@BeforeEach
	public void initMember() {
		joinRequest = new MemberJoinRequest();
		joinRequest.setEmail("22cun2@naver.com");
		joinRequest.setPassword("12345678");
	}
	
	@Test
	public void join() throws Exception {
		joinRequest.setEmail("22cun2@gmail.com");
		Member member = memberService.join(joinRequest);
		Assertions.assertEquals(member.getEmail(), joinRequest.getEmail());
		Assertions.assertTrue(passwordEncoder.matches(joinRequest.getPassword(), member.getPassword()));
	}
	
	@Test
	public void loadUserByUsername() throws Exception {
		Member member = memberService.join(joinRequest);
		UserDetails user = memberService.loadUserByUsername(joinRequest.getEmail());
		Assertions.assertEquals(user.getUsername(), joinRequest.getEmail());
		Assertions.assertTrue(passwordEncoder.matches(joinRequest.getPassword(), user.getPassword()));
	}

}
