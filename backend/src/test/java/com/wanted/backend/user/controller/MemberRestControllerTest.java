package com.wanted.backend.user.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wanted.backend.user.dto.MemberJoinRequest;
import com.wanted.backend.user.service.MemberService;

@SpringBootTest
@AutoConfigureMockMvc
public class MemberRestControllerTest {
	
    @Autowired
    ObjectMapper mapper;

    @Autowired
    MockMvc mvc;
    
	@Autowired
	private MemberService memberService;
    
    private static final String BASIC_URL = "/member";
    private MemberJoinRequest joinRequest;
	
	@BeforeEach
	public void initMember() {
		joinRequest = new MemberJoinRequest();
		joinRequest.setEmail("22cun2@naver.com");
		joinRequest.setPassword("12345678");
	}
    
    @Test
    @DisplayName("회원가입")
    public void join() throws Exception {
        String body = mapper.writeValueAsString(
            joinRequest
        );
    }

}
