package com.example.JAVA_MES_API.websocket.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.JAVA_MES_API.websocket.repository.AlarmRepository;
import com.example.JAVA_MES_API.websocket.dto.AlarmDto;
import com.example.JAVA_MES_API.websocket.dto.MessageDto;
import com.example.JAVA_MES_API.websocket.entity.AlarmWeb;

@Service
public class WebSocketServiceImpl implements WebSocketService {

	private static final Logger log = LoggerFactory.getLogger(WebSocketServiceImpl.class);
	
	
	private AlarmRepository alarmRepository;
	
	public WebSocketServiceImpl (AlarmRepository alarmRepository)
	{
		this.alarmRepository = alarmRepository;
	}

	public void saveAlarm(MessageDto messageDto) {
		
		AlarmDto alarmDto = AlarmDto.fromMessageDto(messageDto);
		
		AlarmWeb alarm  = alarmDto.toEntity();
		
		alarmRepository.save(alarm);
		
		log.info(String.format("웹 소켓 수신 : {}", alarm));
		
	}

	public void sendFcmNotification(AlarmDto alarmDto) {
		log.info("sendFcmNotification");
	}
}
