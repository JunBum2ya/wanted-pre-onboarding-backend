package com.wanted.backend.global.config;

import com.wanted.backend.global.filter.CustomCsrfTokenFilter;
import com.wanted.backend.global.filter.CustomJwtFilter;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CorsFilter;

import com.wanted.backend.global.handler.JwtAccessDeniedHandler;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
	
	public SecurityConfig(TokenProvider tokenProvider,JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,JwtAccessDeniedHandler jwtAccessDeniedHandler) {
        this.tokenProvider = tokenProvider;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
	}

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(o -> o.authenticationEntryPoint(jwtAuthenticationEntryPoint).accessDeniedHandler(jwtAccessDeniedHandler))
                .headers(o -> o.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)) //동일한 origin일 경우에 iframe허용
                .sessionManagement(o -> o.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //jwt를 사용하기 때문에 세션 사용하지 않음
                .authorizeHttpRequests(o -> o.requestMatchers("/member/login","/member/join").permitAll().anyRequest().authenticated())
                .apply(new JwtSecurityConfig(tokenProvider));
        return httpSecurity.build();
    }
    
}
