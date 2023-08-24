package com.wanted.backend.user.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.wanted.backend.global.util.DateUtil;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name="tb_member")
public class Member {
	@Id
	@Column(length=50)
	private final String email;
	@Column(length=150)
	private String password;
	private final LocalDateTime createDate;
	private LocalDateTime updateDate;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tb_user_authority"
			,joinColumns = @JoinColumn(name = "email")
			,inverseJoinColumns = @JoinColumn(name = "authority_name"))
	private final List<Authority> authorityList;

	public Member() {
		this.email = null;
		this.createDate = DateUtil.now();
		this.updateDate = DateUtil.now();
		this.authorityList = new ArrayList<>();
	}

	@Builder
	public Member(String email,String password,List<Authority> authorityList) {
		this.email = email;
		this.password = password;
		this.createDate = DateUtil.now();
		this.updateDate = DateUtil.now();
		this.authorityList = new ArrayList<>();
		if(authorityList != null) {
			this.authorityList.addAll(authorityList);
		}
	}

	public void addAuthority(Authority authority) {
		this.authorityList.add(authority);
	}

	public void removeAuthority(Authority authority) {
		this.authorityList.remove(authority);
	}

	public void update(String password,List<Authority> authorityList) {
		this.password = password;
		this.authorityList.clear();
		if(authorityList != null) {
			this.authorityList.addAll(authorityList);
		}
		this.updateDate = DateUtil.now();
	}

}
