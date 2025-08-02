package com.example.JAVA_MES_API.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "FCM 토큰 발급 응답 DTO")
public class FcmResponeseDto {

	@Schema(description = "성공여부", example = "true")
	private boolean success ;
	
	@Schema(description = "메세지", example = "발급 성공")
	private String message;

		
	public boolean getSuccess() {
		return this.success;
	}
	
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public String getMessage()
	{
		return this.message;
	}
	
	public void setMessage(String message)
	{
		this.message = message;
	}
}
