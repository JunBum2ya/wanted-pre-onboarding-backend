package com.wanted.backend.user.dto;

import lombok.Getter;

/**
 * 회원가입 응답
 */
@Getter
public class MemberJoinResponse {
	private boolean result;
	private String email;
	private String message;
	
	public MemberJoinResponse (boolean result,String email) {
		this.result = result;
		this.email = email;
	}
	
	public MemberJoinResponse (boolean result,String email,String message) {
		this.result = result;
		this.email = email;
		this.message = message;
	}

}
