package com.example.JAVA_MES_API.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.JAVA_MES_API.dao.UserDao;
import com.example.JAVA_MES_API.dto.FcmRequestDto;
import com.example.JAVA_MES_API.dto.FcmResponeseDto;
import com.example.JAVA_MES_API.dto.JwtRequestDto;
import com.example.JAVA_MES_API.dto.JwtResponeseDto;
import com.example.JAVA_MES_API.dto.LoginRequestDto;
import com.example.JAVA_MES_API.dto.LoginResponseDto;
import com.example.JAVA_MES_API.exception.BusinessException;
import com.example.JAVA_MES_API.repository.UserRepository;
import com.example.JAVA_MES_API.security.JwtTokenProvider;
import com.example.JAVA_MES_API.entity.*;
import com.example.JAVA_MES_API.entity.User;

import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

	private UserDao userDao;
	private UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserDao loginDao, UserRepository userRepository) {
		this.userRepository = userRepository;
		this.userDao = loginDao;
	}

	@Override
	public LoginResponseDto searchUserInfo(LoginRequestDto loginRequestDto) {

		LoginResponseDto responseDto = new LoginResponseDto();
		responseDto = userDao.login(loginRequestDto);

		if (responseDto == null) {
			responseDto = new LoginResponseDto();
			responseDto.setSuccess(false);
			responseDto.setLevel(0);
			return responseDto;
		}
		responseDto.setSuccess(true);

		return responseDto;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public FcmResponeseDto updateFcmToken(FcmRequestDto fcmRequestDto) {
		FcmResponeseDto responseDto = new FcmResponeseDto();
		responseDto = userDao.updateFcmToken(fcmRequestDto);

		if (responseDto == null)
			throw new BusinessException("FCM 토큰 업데이트 실패", "FAILD FCM TOKEN UPDATE");

		return responseDto;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public JwtResponeseDto updateJwtToken(JwtRequestDto jwtRequestDto) {

		JwtResponeseDto respons = new JwtResponeseDto();
		Map<String, Object> mapUserInfo = userDao.searchUserInfo(jwtRequestDto);

		LocalDate startDate = LocalDate.parse(mapUserInfo.get("START_DATE").toString());
		LocalDate endDate = LocalDate.parse(mapUserInfo.get("END_DATE").toString());

		long dayDiff = ChronoUnit.DAYS.between(startDate, endDate);

		if (dayDiff <= 0) {
			respons.setSuccess(false);
			respons.setMessage("유효기간이 만료된 계정입니다.");
			respons.setExpiresInDays(0);
			respons.setToken("");

			return respons;
		}

		String jwtTokenString = JwtTokenProvider.generateToken(jwtRequestDto.getUserId(), dayDiff);

		User user = userRepository.findById(jwtRequestDto.getUserId())
				 .orElseThrow(() -> new IllegalArgumentException("User not found"));

		user.setJwtToken(jwtTokenString);
		
		respons.setSuccess(true);
		respons.setMessage("JWT 토큰 발급완료.");
		respons.setExpiresInDays(Math.toIntExact(dayDiff));
		respons.setToken(jwtTokenString);

		return respons;

	}
}
