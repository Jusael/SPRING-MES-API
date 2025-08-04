package com.example.JAVA_MES_API.api.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.JAVA_MES_API.api.dto.FcmRequestDto;
import com.example.JAVA_MES_API.api.dto.FcmResponeseDto;
import com.example.JAVA_MES_API.api.dto.JwtRequestDto;
import com.example.JAVA_MES_API.api.dto.JwtResponeseDto;
import com.example.JAVA_MES_API.api.dto.LoginRequestDto;
import com.example.JAVA_MES_API.api.dto.LoginResponseDto;
import com.example.JAVA_MES_API.api.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/login")
@Tag(name = "로그인 API", description = "로그인 기능 제공")
public class LoginController {
	
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);
	
	private UserService userService;
	
	public LoginController (UserService userService)
	{
		this.userService = userService;
	}
	
	@PostMapping("/post-user-info")
	@Operation(summary = "로그인 정보 확인", description = "APP에서 로그인을 진행할때 실행됩니다.")
	public ResponseEntity<LoginResponseDto> login (@RequestBody LoginRequestDto loginRequestDto) {
		
		LoginResponseDto loginResponse = new LoginResponseDto();
		
		log.info(String.format(" 로그인 요청  postuserinfo UserId: {%s} PassWord {%s}",loginRequestDto.getUserId(), loginRequestDto.getPassWord()));
		
		loginResponse =  userService.searchUserInfo(loginRequestDto);
		
		return ResponseEntity.ok(loginResponse);
	}
	
	@PostMapping("/post-fcm")
	@Operation(summary = "FCM 정보 수신", description = "APP에서 발급받은 토큰을 저장할때 실행됩니다.")
	public ResponseEntity<FcmResponeseDto> updateFcmToken(@RequestBody FcmRequestDto fcmRequestDto) {
		
		FcmResponeseDto responese = new FcmResponeseDto();
		
		responese = userService.updateFcmToken(fcmRequestDto);
		
		return ResponseEntity.ok(responese) ;
	}
	
	@PostMapping("post-jwt")
	@Operation(summary = "JWT 정보 송신", description = "서버에서 발급 받은 JWT토큰을 송신할때 사용됩니다.")
	public ResponseEntity<JwtResponeseDto> updateJwtToken(@RequestBody JwtRequestDto jwtRequestDto) {
		
		JwtResponeseDto responese = new JwtResponeseDto();
		
		responese = userService.updateJwtToken(jwtRequestDto);
		
		return ResponseEntity.ok(responese) ;
	}
}
