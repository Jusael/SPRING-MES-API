package com.example.JAVA_MES_API.dto;

public class LoginResponseDto {

	private boolean success;
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
