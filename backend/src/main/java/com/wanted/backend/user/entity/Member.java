package com.wanted.backend.user.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name="tb_member")
public class Member {
	@Id
	@Column(length=50)
	private String email;
	@Column(length=150)
	private String password;
	private LocalDateTime createDate;
	
	public Member() {
		
	}
	
	public Member(String email,String password) {
		this.email = email;
		this.password = password;
		this.createDate = LocalDateTime.now();
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getPassword() {
		return this.password;
	}
}
