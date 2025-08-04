package com.example.JAVA_MES_API.api.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.JAVA_MES_API.api.dao.UserDao;
import com.example.JAVA_MES_API.api.dto.FcmRequestDto;
import com.example.JAVA_MES_API.api.dto.FcmResponeseDto;
import com.example.JAVA_MES_API.api.dto.JwtRequestDto;
import com.example.JAVA_MES_API.api.dto.JwtResponeseDto;
import com.example.JAVA_MES_API.api.dto.LoginRequestDto;
import com.example.JAVA_MES_API.api.dto.LoginResponseDto;
import com.example.JAVA_MES_API.api.entity.User;
import com.example.JAVA_MES_API.api.exception.BusinessException;
import com.example.JAVA_MES_API.api.repository.UserRepository;
import com.example.JAVA_MES_API.api.security.JwtTokenProvider;

@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	private UserDao userDao;
	private UserRepository userRepository;
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	public UserServiceImpl(UserDao loginDao
			, UserRepository userRepository
			, JwtTokenProvider jwtTokenProvider) {
		this.userRepository = userRepository;
		this.userDao = loginDao;
		this.jwtTokenProvider = jwtTokenProvider;
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

		//NOTE : JPA(ORM) 기능 파악 
		//JPA는 엔터티, 레파지토리로 구분된다.
		// 엔터티는 테이블 엔터티를 의미한다.
		// 레파지토리는 JPA 기능을 인터페이스 한다.
		//JPA는 기본적 코드로 sql을 대체하지만, 특정 DML 경우 Modify로 재정의 하여사용한다.
		//SELECT : FIND BY ID + 컬럼이름
		//INSERT, UPDATE : SAVE 
		//DELETE : deleteById
		
		//영속성에 포함된 엔터티를 SET할경우 dirty update가 가능하여 save는 패스 가능하다.
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

		String jwtTokenString = jwtTokenProvider.generateToken(jwtRequestDto.getUserId(), dayDiff);

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
