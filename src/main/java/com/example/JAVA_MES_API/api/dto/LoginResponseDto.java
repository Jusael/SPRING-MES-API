package com.example.JAVA_MES_API.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "사용자 정보 응답 DTO")
public class LoginResponseDto {

	@Schema(description = "성공여부", example = "true")
	private boolean success;
	
	@Schema(description = "사용자 권한 레벨", example = "10")
	private int level;
	
	public boolean getSucces() {
		return this.success;
	}
	
	public void setSuccess(boolean success) {
		this.success = success; 
	}
	
	public int getLevel()
	{
		return this.level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
}
