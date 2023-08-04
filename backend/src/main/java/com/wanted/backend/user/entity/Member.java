package com.wanted.backend.user.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Member {
	@Id
	@Column(length=20)
	private String email;
	@Column(length=20)
	private String password;
	private LocalDateTime createDate;
}
