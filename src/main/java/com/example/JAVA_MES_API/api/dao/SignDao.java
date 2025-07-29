package com.example.JAVA_MES_API.api.dao;

import com.example.JAVA_MES_API.api.dto.FcmRequestDto;
import com.example.JAVA_MES_API.api.dto.FcmResponeseDto;
import com.example.JAVA_MES_API.api.dto.JwtRequestDto;
import com.example.JAVA_MES_API.api.dto.LoginRequestDto;
import com.example.JAVA_MES_API.api.dto.LoginResponseDto;
import com.example.JAVA_MES_API.api.dto.SignRequestDto;
import com.example.JAVA_MES_API.api.dto.SignResponseDto;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;


public interface SignDao {

	SignResponseDto searchSignInfo(SignRequestDto signRequestDto);
}
