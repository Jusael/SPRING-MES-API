package com.example.JAVA_MES_API.websocket.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.example.JAVA_MES_API.websocket.repository.AlarmRepository;
import com.example.JAVA_MES_API.websocket.dto.AlarmDto;
import com.example.JAVA_MES_API.websocket.dto.MessageDto;
import com.example.JAVA_MES_API.websocket.entity.Alarm;

public class WebSocketServiceImpl implements WebSocketService {

	private AlarmRepository alarmRepository;
	
	public WebSocketServiceImpl (AlarmRepository alarmRepository)
	{
		this.alarmRepository = alarmRepository;
	}

	private static final Logger log = LoggerFactory.getLogger(WebSocketServiceImpl.class);
	
	public void saveAlarm(MessageDto messageDto) {
		
		AlarmDto alarmDto = AlarmDto.fromMessageDto(messageDto);
		
		Alarm alarm  = alarmDto.toEntity();
		
		alarmRepository.save(alarm);
		
	}

	public void sendFcmNotification(AlarmDto alarmDto) {
		log.info("sendFcmNotification");
	}
}
