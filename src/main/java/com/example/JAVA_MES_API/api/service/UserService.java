package com.example.JAVA_MES_API.api.service;

import com.example.JAVA_MES_API.api.dto.FcmRequestDto;
import com.example.JAVA_MES_API.api.dto.FcmResponeseDto;
import com.example.JAVA_MES_API.api.dto.JwtRequestDto;
import com.example.JAVA_MES_API.api.dto.JwtResponeseDto;
import com.example.JAVA_MES_API.api.dto.LoginRequestDto;
import com.example.JAVA_MES_API.api.dto.LoginResponseDto;


public interface UserService {

	public LoginResponseDto searchUserInfo(LoginRequestDto loginRequestDto);
	
	public FcmResponeseDto updateFcmToken(FcmRequestDto fcmRequestDto);
	
	public JwtResponeseDto updateJwtToken(JwtRequestDto jwtRequestDto);
}
