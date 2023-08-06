package com.wanted.backend.global.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomPasswordEncoder implements PasswordEncoder {

	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public CustomPasswordEncoder() {
		this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
	}
	
	@Override
	public String encode(CharSequence rawPassword) {
		// TODO Auto-generated method stub
		return bCryptPasswordEncoder.encode(rawPassword);
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		// TODO Auto-generated method stub
		return bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
	}

}
