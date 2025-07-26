package com.example.JAVA_MES_API.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.JAVA_MES_API.api.dao.UserDaoImpl;
import com.example.JAVA_MES_API.api.dto.SignRequestDto;
import com.example.JAVA_MES_API.api.dto.SignResponseDto;
import com.example.JAVA_MES_API.api.service.SignService;

@RestController
@RequestMapping("/api/sign")
public class SignController {

	private static final Logger log = LoggerFactory.getLogger(SignController.class);

	private final SignService signService;

	@Autowired
	public SignController(SignService signService) {
		this.signService = signService;
	}

	@GetMapping("/get-search-sign-info")
	public SignResponseDto searchSignInfo(SignRequestDto signRequestDto) {

		return signService.searchSignInfo(signRequestDto);
	}
	
	@GetMapping("get-user-sign-info")
	public SignResponseDto searchSignRoleInfo(SignRequestDto signRequestDto)
	{
		return signService.searchSignRoleInfo(signRequestDto);
	}
	
	@PostMapping("/post-signing")
	public SignResponseDto updateSignResult(SignRequestDto signRequestDto)
	{
		return null;
	}
}
