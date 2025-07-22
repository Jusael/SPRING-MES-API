package com.example.JAVA_MES_API.websocket.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.example.JAVA_MES_API.websocket.dto.AlarmDto;
import com.example.JAVA_MES_API.websocket.dto.MessageDto;
import com.example.JAVA_MES_API.websocket.service.WebSocketService;

@Controller
public class WebsocketController {

	private WebSocketService webSocketService;
	
	public WebsocketController(WebSocketService webSocketService)
	{
		this.webSocketService = webSocketService;
	}
	
	@MessageMapping("/send")
	@SendTo("topic/alarms")
	public void broadcase(MessageDto messageDto)
	{
		webSocketService.saveAlarm(messageDto);
		
		webSocketService.sendFcmNotification(null);
	}
	
}
