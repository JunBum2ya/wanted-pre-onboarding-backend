package com.wanted.backend.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberLoginResponse {
	private boolean result;
	private String email;
	private String token;
	private String message;

	public MemberLoginResponse (boolean result,String email,String message,String token) {
		this.result = result;
		this.email = email;
		this.message = message;
		this.token = token;
	}

}
