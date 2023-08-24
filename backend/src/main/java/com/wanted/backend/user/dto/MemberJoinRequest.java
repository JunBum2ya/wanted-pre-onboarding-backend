package com.wanted.backend.user.dto;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.wanted.backend.user.entity.Member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

public class MemberJoinRequest {
	@NotNull(message="이메일을 입력하세요.")
	@Email(message="이메일 양식을 입력하세요.")
	private String email;
	@NotNull(message="패스워드를 입력하세요.")
	@Size(min = 8,message="패스워드를 8자리 이상 입력하세요.")
	private String password;
	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Member toEntity(PasswordEncoder passwordEncoder) {
		return Member.builder().email(email).password(passwordEncoder.encode(password)).build();
	}
}
