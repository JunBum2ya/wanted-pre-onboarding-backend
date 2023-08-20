package com.wanted.backend.user.controller;

import com.wanted.backend.global.config.TokenProvider;
import com.wanted.backend.global.filter.CustomJwtFilter;
import com.wanted.backend.user.dto.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wanted.backend.user.entity.Member;
import com.wanted.backend.user.service.MemberService;

import jakarta.validation.Valid;

@RestController
public class MemberRestController {
	
	private final MemberService memberService;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;
	private final TokenProvider tokenProvider;
	
	public MemberRestController(MemberService memberService,AuthenticationManagerBuilder authenticationManagerBuilder,TokenProvider tokenProvider) {
		this.memberService = memberService;
		this.authenticationManagerBuilder = authenticationManagerBuilder;
		this.tokenProvider = tokenProvider;
	}
	
	@PostMapping(value = "/member/login")
	public ResponseEntity<MemberLoginResponse> login(@Valid @RequestBody MemberLoginRequest request) {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword());
		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = tokenProvider.createToken(authentication);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(CustomJwtFilter.AUTHORIZATION_HEADER,"Bearer " + jwt);
		return new ResponseEntity<>(new MemberLoginResponse(true,request.getEmail(),"로그인 성공",jwt),HttpStatus.OK);
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

	@GetMapping(value = "/member/test")
	public ResponseEntity<String> test(Authentication authentication) {
		CustomUserDetails test = (CustomUserDetails) authentication.getPrincipal();
		return ResponseEntity.ok("test 성공");
	}

}
