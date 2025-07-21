package com.example.JAVA_MES_API.service;

import java.util.List;

import com.example.JAVA_MES_API.dto.AlarmsRequestDto;
import com.example.JAVA_MES_API.dto.AlarmsResponseDto;

public interface AlarmService {
	
	List<AlarmsResponseDto> searchUnActionAlarm(AlarmsRequestDto alarmsRequestDto);
	
}
