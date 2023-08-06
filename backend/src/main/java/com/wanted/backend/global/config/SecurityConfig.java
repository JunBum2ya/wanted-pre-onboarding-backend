package com.wanted.backend.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFilter;

import com.wanted.backend.user.service.MemberService;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
	
	private final MemberService memberService;
	private final PasswordEncoder passwordEncoder;
	
	public SecurityConfig(MemberService memberService,PasswordEncoder passwordEncoder) {
		this.memberService = memberService;
		this.passwordEncoder = passwordEncoder;
	}
	
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((auth) -> auth
        		.requestMatchers("/member/join")
        		.permitAll()
        		.anyRequest()
        		.authenticated())
        .csrf((csrf) -> csrf.disable());
        return http.build();
    }
    
    public AuthenticationManager authenticationManager(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(memberService).passwordEncoder(passwordEncoder);
    	return auth.build();
    }
    
    private AuthenticationFilter getAuthenticationFilter() throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter();
        AuthenticationManagerBuilder builder = new AuthenticationManagerBuilder(objectPostProcessor);
        authenticationFilter.setAuthenticationManager(authenticationManager(builder));
        return authenticationFilter;
    }
    
}
