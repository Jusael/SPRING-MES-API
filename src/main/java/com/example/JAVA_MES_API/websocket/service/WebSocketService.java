package com.example.JAVA_MES_API.websocket.service;

import com.example.JAVA_MES_API.websocket.dto.AlarmDto;
import com.example.JAVA_MES_API.websocket.dto.MessageDto;


public interface WebSocketService {

	void saveAlarm(MessageDto messageDto);
	
	void sendFcmNotification(AlarmDto alarmDto);
}
