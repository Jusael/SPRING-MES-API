package com.example.JAVA_MES_API.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "JWT 발급 요청 DTO")
public class JwtRequestDto {

	@Schema(description = "사용자 ID", example = "admin")
	private String userId;
	
	@Schema(description = "JWT 토큰", example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTc1MzQ0NzczMywiZXhwIjozMzk0MzU2NTMzfQ.GoZxdD78Zzf-YSRh3tYA5IEXkjLLEUTWtPreJb6ZunQ")
	private String jwtToken;
	
	public String getUserId() {
		return this.userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	public String getFcmToken() {
		return this.jwtToken;
	}
	
	public void setFcmToken(String fcmToken) {
		this.jwtToken = fcmToken;
	}
}
