package com.example.JAVA_MES_API.api.dao;

import com.example.JAVA_MES_API.api.dto.SignRequestDto;
import com.example.JAVA_MES_API.api.dto.SignResponseDto;


public interface SignDao {

	SignResponseDto searchSignInfo(SignRequestDto signRequestDto);
}
