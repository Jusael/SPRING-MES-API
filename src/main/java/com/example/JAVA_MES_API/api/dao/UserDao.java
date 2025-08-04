package com.example.JAVA_MES_API.api.dao;

import java.util.Map;

import com.example.JAVA_MES_API.api.dto.FcmRequestDto;
import com.example.JAVA_MES_API.api.dto.FcmResponeseDto;
import com.example.JAVA_MES_API.api.dto.JwtRequestDto;
import com.example.JAVA_MES_API.api.dto.LoginRequestDto;
import com.example.JAVA_MES_API.api.dto.LoginResponseDto;


public interface UserDao {

	LoginResponseDto login(LoginRequestDto loginRequestDto);
	
	FcmResponeseDto updateFcmToken(FcmRequestDto fcmRequstDto);
	
	Map<String, Object> searchUserInfo(JwtRequestDto jwtRequestDto);
}
