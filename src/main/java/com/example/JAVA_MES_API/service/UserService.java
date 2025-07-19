package com.example.JAVA_MES_API.service;

import org.springframework.stereotype.Service;

import com.example.JAVA_MES_API.dto.FcmRequestDto;
import com.example.JAVA_MES_API.dto.FcmResponeseDto;
import com.example.JAVA_MES_API.dto.JwtRequestDto;
import com.example.JAVA_MES_API.dto.JwtResponeseDto;
import com.example.JAVA_MES_API.dto.LoginRequestDto;
import com.example.JAVA_MES_API.dto.LoginResponseDto;


public interface UserService {

	public LoginResponseDto searchUserInfo(LoginRequestDto loginRequestDto);
	
	public FcmResponeseDto updateFcmToken(FcmRequestDto fcmRequestDto);
	
	public JwtResponeseDto updateJwtToken(JwtRequestDto jwtRequestDto);
}
