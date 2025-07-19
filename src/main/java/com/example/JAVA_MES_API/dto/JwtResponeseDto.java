package com.example.JAVA_MES_API.dto;

public class JwtResponeseDto {

	
	private boolean success ;
	private String token;
	private int expiresInDays;
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
