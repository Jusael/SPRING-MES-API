package com.example.JAVA_MES_API.api.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.JAVA_MES_API.api.dao.UserDaoImpl;
import com.example.JAVA_MES_API.api.dto.FcmRequestDto;
import com.example.JAVA_MES_API.api.dto.FcmResponeseDto;
import com.example.JAVA_MES_API.api.dto.JwtRequestDto;
import com.example.JAVA_MES_API.api.dto.JwtResponeseDto;
import com.example.JAVA_MES_API.api.dto.LoginRequestDto;
import com.example.JAVA_MES_API.api.dto.LoginResponseDto;
import com.example.JAVA_MES_API.api.service.UserService;


@RestController
@RequestMapping("/api/login")
public class LoginController {
	
	private static final Logger log = LoggerFactory.getLogger(UserDaoImpl.class);
	
	private UserService userService;
	
	public LoginController (UserService userService)
	{
		this.userService = userService;
	}
	
	@PostMapping("/post-user-info")
	public ResponseEntity<LoginResponseDto> login (@RequestBody LoginRequestDto loginRequestDto) {
		
		LoginResponseDto loginResponse = new LoginResponseDto();
		
		log.info("--======================================================");
		log.info("로그인 요청  postuserinfo");
		log.info(String.format("UserId: {%s} PassWord {%s}",loginRequestDto.getUserId(), loginRequestDto.getPassWord()));
		log.info("--======================================================");
		
		loginResponse =  userService.searchUserInfo(loginRequestDto);
		
		return ResponseEntity.ok(loginResponse);
	}
	
	@PostMapping("/post-fcm")
	public ResponseEntity<FcmResponeseDto> updateFcmToken(@RequestBody FcmRequestDto fcmRequestDto) {
		
		FcmResponeseDto responese = new FcmResponeseDto();
		
		responese = userService.updateFcmToken(fcmRequestDto);
		
		return ResponseEntity.ok(responese) ;
	}
	
	@PostMapping("post-jwt")
	public ResponseEntity<JwtResponeseDto> updateJwtToken(@RequestBody JwtRequestDto jwtRequestDto) {
		
		JwtResponeseDto responese = new JwtResponeseDto();
		
		responese = userService.updateJwtToken(jwtRequestDto);
		
		return ResponseEntity.ok(responese) ;
	}
}
