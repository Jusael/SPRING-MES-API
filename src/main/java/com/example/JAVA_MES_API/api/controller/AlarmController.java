package com.example.JAVA_MES_API.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.JAVA_MES_API.api.dao.UserDaoImpl;
import com.example.JAVA_MES_API.api.dto.AlarmsRequestDto;
import com.example.JAVA_MES_API.api.dto.AlarmsResponseDto;
import com.example.JAVA_MES_API.api.dto.LoginResponseDto;
import com.example.JAVA_MES_API.api.service.AlarmService;

@RestController
@RequestMapping("api/alarm")
public class AlarmController {

	private static final Logger log = LoggerFactory.getLogger(AlarmController.class);

	AlarmService alarmService;

	public AlarmController(AlarmService alarmService) {
		this.alarmService = alarmService;
	}

	@GetMapping("/get-incoming-alarm-but-unread")
	public ResponseEntity<List<AlarmsResponseDto>> searchUnReadAlarm(AlarmsRequestDto alarmsRequestDto){

		List<AlarmsResponseDto> list = alarmService.searchUnActionAlarm(alarmsRequestDto);

		log.info("--======================================================");
		log.info("앱 종료 후 알람 클릭시 누락 알람 리스트 조회 URL: /get-incoming-alarm-but-unread");
		log.info(String.format("UserId: {%s}", alarmsRequestDto.getUserId()));
		log.info("--======================================================");
		
		for (AlarmsResponseDto alarmsResponseDto : list) {
			log.info(String.format("AppAlarmId :  {%s} UserId : {%s} ", alarmsResponseDto.getAppAlarmId(), alarmsResponseDto.getUserId()));	
		}
		
		
		
		return ResponseEntity.ok(list);
	}

	@GetMapping("/post-alarm-status-controll")
	public void readOrDeleteAarm(AlarmsRequestDto alarmsRequestDto)
	{
		alarmService.readOrDeleteAarm(alarmsRequestDto);		
	}
}
