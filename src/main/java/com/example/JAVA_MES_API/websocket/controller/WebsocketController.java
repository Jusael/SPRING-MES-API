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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Controller
@Tag(name = "웹소켓 API", description = "웹소켓을 관리 합니다.")
public class WebsocketController {

	private static final Logger log = LoggerFactory.getLogger(WebsocketController.class);
	
	private WebSocketService webSocketService;
	
	public WebsocketController(WebSocketService webSocketService)
	{
		this.webSocketService = webSocketService;
	}
	
	@MessageMapping("/send")
	@Operation(summary = "알람관리", description = "웹 소켓에서 전달 받은 메세지를 FCM 메세지로 사용자에게 전송합니다.")
	public void broadcase(MessageDto messageDto)
	{
		webSocketService.saveAlarm(messageDto);
	}
	
}
