package com.example.JAVA_MES_API.api.service;

import java.util.List;

import com.example.JAVA_MES_API.api.dto.AlarmsRequestDto;
import com.example.JAVA_MES_API.api.dto.AlarmsResponseDto;

public interface AlarmService {
	
	List<AlarmsResponseDto> searchUnActionAlarm(AlarmsRequestDto alarmsRequestDto);
	
	
	void readOrDeleteAarm(AlarmsRequestDto alarmsRequestDto);
}
