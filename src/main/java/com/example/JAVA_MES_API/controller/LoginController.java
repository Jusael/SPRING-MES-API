package com.example.JAVA_MES_API.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.JAVA_MES_API.dto.FcmRequestDto;
import com.example.JAVA_MES_API.dto.FcmResponeseDto;
import com.example.JAVA_MES_API.dto.JwtRequestDto;
import com.example.JAVA_MES_API.dto.JwtResponeseDto;
import com.example.JAVA_MES_API.dto.LoginRequestDto;
import com.example.JAVA_MES_API.dto.LoginResponseDto;
import com.example.JAVA_MES_API.service.UserService;


@RestController
@RequestMapping("/api/login")
public class LoginController {

	private UserService userService;
	
	public LoginController (UserService userService)
	{
		this.userService = userService;
	}
	
	@PostMapping("/postuserinfo")
	public ResponseEntity<LoginResponseDto> login (@RequestBody LoginRequestDto loginRequestDto) {
		
		LoginResponseDto loginResponse = new LoginResponseDto();
		
		loginResponse =  userService.searchUserInfo(loginRequestDto);
		
		System.out.println("loginResponse: " + loginResponse);
		
		return ResponseEntity.ok(loginResponse);
	}
	
	@PostMapping("/postfcm")
	public ResponseEntity<FcmResponeseDto> updateFcmToken(@RequestBody FcmRequestDto fcmRequestDto) {
		
		FcmResponeseDto responese = new FcmResponeseDto();
		
		responese = userService.updateFcmToken(fcmRequestDto);
		
		return ResponseEntity.ok(responese) ;
	}
	
	@PostMapping("postjwt")
	public ResponseEntity<JwtResponeseDto> updateJwtToken(@RequestBody JwtRequestDto jwtRequestDto) {
		
		JwtResponeseDto responese = new JwtResponeseDto();
		
		responese = userService.updateJwtToken(jwtRequestDto);
		
		return ResponseEntity.ok(responese) ;
	}
}
