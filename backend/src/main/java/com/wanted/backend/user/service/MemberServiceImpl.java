package com.wanted.backend.user.service;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.wanted.backend.user.dto.CustomUserDetails;
import com.wanted.backend.user.dto.MemberJoinRequest;
import com.wanted.backend.user.entity.Member;
import com.wanted.backend.user.repository.MemberRepository;

import jakarta.transaction.Transactional;

@Service
public class MemberServiceImpl implements MemberService {

	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	
	public MemberServiceImpl(MemberRepository memberRepository,PasswordEncoder passwordEncoder) {
		this.memberRepository = memberRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Member member = memberRepository.findById(username).orElseThrow(() -> new UsernameNotFoundException(username));
		return new CustomUserDetails(member);
	}
	
	/**
	 * 회원가입
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@Override
	@Transactional
	public Member join(MemberJoinRequest request) throws Exception {
		if(memberRepository.findById(request.getEmail()).isEmpty()) {
			return memberRepository.save(request.toEntity(passwordEncoder));
		} else {
			throw new DuplicateKeyException("이미 회원가입된 이메일입니다.");
		}
	}

}
