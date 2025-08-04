package com.example.JAVA_MES_API.websocket.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.JAVA_MES_API.websocket.dto.AlarmDto;
import com.example.JAVA_MES_API.websocket.dto.MessageDto;
import com.example.JAVA_MES_API.websocket.entity.AlarmWeb;
import com.example.JAVA_MES_API.websocket.entity.FcmSavedEvent;
import com.example.JAVA_MES_API.websocket.repository.AlarmRepository;


@Service
public class WebSocketServiceImpl implements WebSocketService {

	private static final Logger log = LoggerFactory.getLogger(WebSocketServiceImpl.class);
	
	
	private AlarmRepository alarmRepository;
    private ApplicationEventPublisher applicationEventPublisher;
	
	public WebSocketServiceImpl (AlarmRepository alarmRepository,  ApplicationEventPublisher applicationEventPublisher)
	{
		this.alarmRepository = alarmRepository;
		this.applicationEventPublisher = applicationEventPublisher;
	}

	@Transactional(rollbackFor = Exception.class)
	public void saveAlarm(MessageDto messageDto) {
		
		AlarmDto alarmDto = AlarmDto.fromMessageDto(messageDto);
		
		AlarmWeb alarm  = alarmRepository.save(alarmDto.toEntity()); 
		
		applicationEventPublisher.publishEvent(new FcmSavedEvent(alarm));
	}
	

	public void sendFcmNotification(AlarmDto alarmDto) {
		log.info("sendFcmNotification");
	}
}


