package com.wanted.backend.global.handler;

import lombok.Getter;

@Getter
public class ErrorResponse {
	private boolean result;
	private String message;
	
	public ErrorResponse() {
		this.result = false;
		this.message = "ERROR OCCUR";
	}
	
	public ErrorResponse(String message) {
		this.result = false;
		this.message = message;
	}

}
