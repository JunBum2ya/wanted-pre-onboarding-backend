package com.wanted.backend.user.dto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails,Serializable {

	private static final long serialVersionUID = 174726374856727L;
	
	private final String username;
	private final String password;
	private final List<? extends GrantedAuthority> authorities;
	
	public CustomUserDetails(String userId,String password,List<? extends GrantedAuthority> authorities) {
		this.username = userId;
		this.password = password;
		this.authorities = authorities;
	}
	
	public CustomUserDetails(String userId,String password) {
		this.username = userId;
		this.password = password;
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority("DEFAULT");
		this.authorities = Arrays.asList(authority);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
