package com.example.JAVA_MES_API.api.service;

import com.example.JAVA_MES_API.api.dto.SignRequestDto;
import com.example.JAVA_MES_API.api.dto.SignResponseDto;

public interface SignService {

	SignResponseDto searchSignInfo(SignRequestDto signRequestDto);
	
	SignResponseDto searchSignRoleInfo(SignRequestDto signRequestDto);
	
	SignResponseDto updateSignResult(SignRequestDto signRequestDto);
	
}

