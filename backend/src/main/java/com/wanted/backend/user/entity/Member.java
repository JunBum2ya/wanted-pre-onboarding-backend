package com.wanted.backend.user.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.wanted.backend.global.util.DateUtil;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name="tb_member")
public class Member {
	@Id
	@Column(length=50)
	private String email;
	@Column(length=150)
	private String password;
	private final LocalDateTime createDate;
	private LocalDateTime updateDate;
	@ManyToMany
	@JoinTable(name = "tb_user_authority"
			,joinColumns = @JoinColumn(name = "email")
			,inverseJoinColumns = @JoinColumn(name = "authority_name"))
	private final List<Authority> authorityList;
	
	public Member() {
		this.createDate = DateUtil.now();
		this.updateDate = DateUtil.now();
		this.authorityList = new ArrayList<>();
	}
	
	public Member(String email,String password) {
		this();
		this.email = email;
		this.password = password;
	}

}
