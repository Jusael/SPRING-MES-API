package com.example.JAVA_MES_API.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.JAVA_MES_API.dao.UserDao;
import com.example.JAVA_MES_API.dto.FcmRequestDto;
import com.example.JAVA_MES_API.dto.FcmResponeseDto;
import com.example.JAVA_MES_API.dto.LoginRequestDto;
import com.example.JAVA_MES_API.dto.LoginResponseDto;
import com.example.JAVA_MES_API.exception.BusinessException;

@Service
public class UserServiceImpl implements UserService {

	private UserDao userDao;

	@Autowired
	public UserServiceImpl(UserDao loginDao) {
		this.userDao = loginDao;
	}

	@Override
	public LoginResponseDto searchUserInfo(LoginRequestDto loginRequestDto) {

		LoginResponseDto responseDto = new LoginResponseDto();
		responseDto = userDao.login(loginRequestDto);

		if (responseDto == null)
			throw new BusinessException("사용자 정보 없음", "USER_NOT_MATCH");

		responseDto.setSuccess(true);

		return responseDto;
	}

	
	public FcmResponeseDto updateFcmToken(FcmRequestDto fcmRequestDto){
		FcmResponeseDto responseDto = new FcmResponeseDto();
		responseDto = userDao.updateFcmToken(fcmRequestDto);

		if (responseDto == null)
			throw new BusinessException("FCM 토큰 업데이트 실패", "FAILD FCM TOKEN UPDATE");

		return responseDto;
	}

}
