package com.example.JAVA_MES_API.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.JAVA_MES_API.dto.LoginRequestDto;
import com.example.JAVA_MES_API.dto.LoginResponseDto;
import com.example.JAVA_MES_API.service.UserService;


@RestController
@RequestMapping("/api")
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
	
	
}
