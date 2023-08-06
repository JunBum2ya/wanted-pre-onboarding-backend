package com.wanted.backend.user.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.wanted.backend.user.dto.MemberJoinRequest;
import com.wanted.backend.user.entity.Member;

public interface MemberService extends UserDetailsService {
	public Member join(MemberJoinRequest request)  throws Exception;
}
