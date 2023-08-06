package com.wanted.backend.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wanted.backend.user.dto.MemberJoinRequest;
import com.wanted.backend.user.dto.MemberJoinResponse;
import com.wanted.backend.user.entity.Member;
import com.wanted.backend.user.service.MemberService;

import jakarta.validation.Valid;

@RestController
public class MemberRestController {
	
	private final MemberService memberService;
	
	public MemberRestController(MemberService memberService) {
		this.memberService = memberService; 
	}
	
	/**
	 * 회원가입
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@PostMapping(value="/member/join")
	public ResponseEntity<MemberJoinResponse> join(@Valid @RequestBody MemberJoinRequest request) throws Exception {
		Member member = memberService.join(request);
		return ResponseEntity.ok(new MemberJoinResponse(true,member.getEmail(),"success join"));
	}

}
