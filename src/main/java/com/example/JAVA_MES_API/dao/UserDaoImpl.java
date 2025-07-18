package com.example.JAVA_MES_API.dao;

import java.io.Console;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.JAVA_MES_API.dto.FcmRequestDto;
import com.example.JAVA_MES_API.dto.FcmResponeseDto;
import com.example.JAVA_MES_API.dto.LoginRequestDto;
import com.example.JAVA_MES_API.dto.LoginResponseDto;
import com.example.JAVA_MES_API.exception.BusinessException;

@Repository
public class UserDaoImpl implements UserDao {

	private static final Logger log = LoggerFactory.getLogger(UserDaoImpl.class);

	@Autowired
	private SqlSession sqlSession;
	private String NAME_SPACE = "userMapper.";

	@Override
	public LoginResponseDto login(LoginRequestDto loginRequestDto) {

		LoginResponseDto loginResponseDto = new LoginResponseDto();

		loginResponseDto = sqlSession.selectOne(NAME_SPACE + "login", loginRequestDto);

		if (loginResponseDto == null)
				throw new BusinessException("사용자 정보 없음", "USER_NOT MATCH") ;

		return loginResponseDto;
	}
	
	@Override
	public FcmResponeseDto updateFcmToken(FcmRequestDto fcmRequstDto) {
		
		return null;
	}

}
