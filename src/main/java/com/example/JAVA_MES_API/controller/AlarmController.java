package com.example.JAVA_MES_API.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.JAVA_MES_API.dto.LoginResponseDto;

@RestController
@RequestMapping("/alarm")
public class AlarmController {

	
	@GetMapping("/getincomingalarmbutunread")
	public ResponseEntity<LoginResponseDto> searchUnReadAlarm(){
		return null;
	}
	
}
