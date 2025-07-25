package com.example.JAVA_MES_API.websocket.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.example.JAVA_MES_API.websocket.dto.AlarmDto;
import com.example.JAVA_MES_API.websocket.dto.MessageDto;
import com.example.JAVA_MES_API.websocket.exception.WebSocketExceptionHandler;
import com.example.JAVA_MES_API.websocket.service.WebSocketService;

@Controller
public class WebsocketController {

	private static final Logger log = LoggerFactory.getLogger(WebsocketController.class);
	
	private WebSocketService webSocketService;
	
	public WebsocketController(WebSocketService webSocketService)
	{
		this.webSocketService = webSocketService;
	}
	
	@MessageMapping("/send")
	public void broadcase(MessageDto messageDto)
	{
		log.info("웹소켓 컨트롤러 수신");
		
		webSocketService.saveAlarm(messageDto);
		
		webSocketService.sendFcmNotification(null);
	}
	
}
