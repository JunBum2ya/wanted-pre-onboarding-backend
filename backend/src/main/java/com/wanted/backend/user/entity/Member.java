package com.wanted.backend.user.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tb_user_authority"
			,joinColumns = @JoinColumn(name = "email")
			,inverseJoinColumns = @JoinColumn(name = "authority_name"))
	private final Set<Authority> authorityList;
	
	public Member() {
		this.createDate = DateUtil.now();
		this.updateDate = DateUtil.now();
		this.authorityList = new HashSet<>();
	}
	
	public Member(String email,String password) {
		this();
		this.email = email;
		this.password = password;
	}

	public void addAuthority(Authority authority) {
		this.authorityList.add(authority);
	}

}
