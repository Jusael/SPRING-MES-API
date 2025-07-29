
package com.example.JAVA_MES_API.api.dao;

import java.io.Console;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.JAVA_MES_API.api.dto.FcmRequestDto;
import com.example.JAVA_MES_API.api.dto.FcmResponeseDto;
import com.example.JAVA_MES_API.api.dto.JwtRequestDto;
import com.example.JAVA_MES_API.api.dto.LoginRequestDto;
import com.example.JAVA_MES_API.api.dto.LoginResponseDto;
import com.example.JAVA_MES_API.api.dto.SignRequestDto;
import com.example.JAVA_MES_API.api.dto.SignResponseDto;
import com.example.JAVA_MES_API.api.exception.BusinessException;

@Repository
public class SignDaoImpl implements SignDao {

	private static final Logger log = LoggerFactory.getLogger(SignDaoImpl.class);

	@Autowired
	private SqlSession sqlSession;
	private String NAME_SPACE = "signMapper.";
	
	
	@Override
	public SignResponseDto searchSignInfo(SignRequestDto signRequestDto) {
		
		 SignResponseDto responseDto = sqlSession.selectOne(NAME_SPACE + "signSearchInfo", signRequestDto);
		 
		 return responseDto;
	}
}
