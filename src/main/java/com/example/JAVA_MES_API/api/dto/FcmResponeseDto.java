package com.example.JAVA_MES_API.api.dto;

public class FcmResponeseDto {

	
	private boolean success ;
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
