package com.example.JAVA_MES_API.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "JWT 발급 응답 DTO")
public class JwtResponeseDto {

	@Schema(description = "성공여부", example = "true")
	private boolean success ;
	
	@Schema(description = "JWT 토큰", example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTc1MzQ0NzczMywiZXhwIjozMzk0MzU2NTMzfQ.GoZxdD78Zzf-YSRh3tYA5IEXkjLLEUTWtPreJb6ZunQ")
	private String token;
	
	@Schema(description = "JWT 토큰 유효일자", example = "500")
	private int expiresInDays;
	
	@Schema(description = "메세지", example = "발급에 성공 하였습니다.")
	private String message;

		
	public boolean getSuccess() {
		return this.success;
	}
	
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public String getToken() {
		return this.token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
	public int getExpiresInDays() {
		return this.expiresInDays;
	}
	
	public void setExpiresInDays(int expiresInDays)
	{
		this.expiresInDays = expiresInDays;
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
