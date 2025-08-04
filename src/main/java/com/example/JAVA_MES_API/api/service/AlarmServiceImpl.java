package com.example.JAVA_MES_API.api.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.JAVA_MES_API.api.dto.AlarmsRequestDto;
import com.example.JAVA_MES_API.api.dto.AlarmsResponseDto;
import com.example.JAVA_MES_API.api.entity.Alarm;
import com.example.JAVA_MES_API.api.repository.AlarmRepository;

@Service
public class AlarmServiceImpl implements AlarmService {

	private AlarmRepository alarmRepository;
	private static final Logger log = LoggerFactory.getLogger(AlarmServiceImpl.class);
	
	@Autowired
	public AlarmServiceImpl (AlarmRepository alarmRepository)
	{
		this.alarmRepository = alarmRepository;
	}
	
	@Override
	public List<AlarmsResponseDto> searchUnActionAlarm(AlarmsRequestDto alarmsRequestDto) {

		List<Alarm> list = alarmRepository.findByUserIdAndAlarmStatusIsNull(alarmsRequestDto.getUserId());
				
		return list.stream().map(AlarmsResponseDto::fromEntity).toList();
	}
	
	@Override
	public void readOrDeleteAarm(AlarmsRequestDto alarmsRequestDto)
	{
		int appId = Integer.parseInt(alarmsRequestDto.getAppAlarmId());
		Alarm alarm = alarmRepository.findById(appId).orElseThrow(() -> new RuntimeException("Alarm not found"));
		
		alarm.setAlarmStatus(alarmsRequestDto.getAlarmStatus());
		
		alarmRepository.save(alarm);
	}
}
