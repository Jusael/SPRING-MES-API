package com.example.JAVA_MES_API.api.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.JAVA_MES_API.api.dto.FcmRequestDto;
import com.example.JAVA_MES_API.api.dto.FcmResponeseDto;
import com.example.JAVA_MES_API.api.dto.JwtRequestDto;
import com.example.JAVA_MES_API.api.dto.LoginRequestDto;
import com.example.JAVA_MES_API.api.dto.LoginResponseDto;
import com.example.JAVA_MES_API.api.exception.BusinessException;

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
				throw new BusinessException("사용자 정보 없음", "USER NOT MATCH") ;

		return loginResponseDto;
	}
	
	@Override
	public FcmResponeseDto updateFcmToken(FcmRequestDto fcmRequstDto) {
		
		FcmResponeseDto responeseDto = new FcmResponeseDto();

		int result = sqlSession.update(NAME_SPACE + "updateFcm", fcmRequstDto);

		if(result < 0)
			throw new BusinessException("FCM 업데이트 실패", "FCM UPDATE ERROR");
		
		responeseDto.setSuccess(true);
		responeseDto.setMessage("FCM 토큰 업데이트 완료");
		
		return responeseDto;
	}

	@Override
	public Map<String, Object> searchUserInfo(JwtRequestDto jwtRequestDto){
		
		Map<String, Object> resultMap = sqlSession.selectOne(NAME_SPACE+ "getUserInfo", jwtRequestDto);
		
		if(resultMap == null)
			throw new BusinessException("사용자 조회 실패", "NOT FIND USER INFO");
		
		return resultMap;
	}
}
