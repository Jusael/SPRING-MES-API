package com.example.JAVA_MES_API.dao;

import com.example.JAVA_MES_API.dto.FcmRequestDto;
import com.example.JAVA_MES_API.dto.FcmResponeseDto;
import com.example.JAVA_MES_API.dto.JwtRequestDto;
import com.example.JAVA_MES_API.dto.LoginRequestDto;
import com.example.JAVA_MES_API.dto.LoginResponseDto;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;


public interface UserDao {

	LoginResponseDto login(LoginRequestDto loginRequestDto);
	
	FcmResponeseDto updateFcmToken(FcmRequestDto fcmRequstDto);
	
	Map<String, Object> searchUserInfo(JwtRequestDto jwtRequestDto);
}
