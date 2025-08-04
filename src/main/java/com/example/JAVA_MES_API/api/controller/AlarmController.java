package com.example.JAVA_MES_API.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.JAVA_MES_API.api.dto.AlarmsRequestDto;
import com.example.JAVA_MES_API.api.dto.AlarmsResponseDto;
import com.example.JAVA_MES_API.api.service.AlarmService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/alarm")
@Tag(name = "알람 API", description = "알람 관련 기능 제공")
public class AlarmController {

	private static final Logger log = LoggerFactory.getLogger(AlarmController.class);

	AlarmService alarmService;

	public AlarmController(AlarmService alarmService) {
		this.alarmService = alarmService;
	}

	@GetMapping("/get-incoming-alarm-but-unread")
	@Operation(summary = "읽지 않은 알람 재전송", description = "FCM이 수신되지 않은 상황을 위한 알람 조회후 전송합니다.")
	public ResponseEntity<List<AlarmsResponseDto>> searchUnReadAlarm(AlarmsRequestDto alarmsRequestDto) {

		List<AlarmsResponseDto> list = alarmService.searchUnActionAlarm(alarmsRequestDto);

		log.info(String.format("searchUnReadAlarm UserId: {%s}", alarmsRequestDto.getUserId()));

		for (AlarmsResponseDto alarmsResponseDto : list) {
			log.info(String.format("AppAlarmId :  {%s} UserId : {%s} ", alarmsResponseDto.getAppAlarmId(),
					alarmsResponseDto.getUserId()));
		}

		return ResponseEntity.ok(list);
	}

	@PostMapping("/post-alarm-status-controll")
	@Operation(summary = "알람에 대한 사용자 행위 기록", description = "FCM 알람을 읽거나 삭제할때 실행합니다.")
	public void readOrDeleteAarm(@RequestBody AlarmsRequestDto alarmsRequestDto) {
		alarmService.readOrDeleteAarm(alarmsRequestDto);
	}
}
