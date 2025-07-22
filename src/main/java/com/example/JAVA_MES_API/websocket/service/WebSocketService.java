package com.example.JAVA_MES_API.websocket.service;

import org.springframework.stereotype.Service;

import com.example.JAVA_MES_API.api.entity.Alarm;
import com.example.JAVA_MES_API.websocket.dto.AlarmDto;
import com.example.JAVA_MES_API.websocket.dto.MessageDto;

@Service
public interface WebSocketService {

	void saveAlarm(MessageDto messageDto);
	
	void sendFcmNotification(AlarmDto alarmDto);
}
