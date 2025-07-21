package com.example.JAVA_MES_API.service;

import java.util.List;

import com.example.JAVA_MES_API.dto.AlarmsRequestDto;
import com.example.JAVA_MES_API.dto.AlarmsResponseDto;
import com.example.JAVA_MES_API.entity.Alarm;
import com.example.JAVA_MES_API.repository.AlarmRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
public class AlarmServiceImpl implements AlarmService {

	private AlarmRepository alarmRepository;
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
}
