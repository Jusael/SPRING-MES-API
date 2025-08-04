package com.example.JAVA_MES_API.websocket.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import com.example.JAVA_MES_API.api.dto.ErrorResponse;
import com.example.JAVA_MES_API.api.exception.BusinessException;

@Controller
public class WebSocketExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(WebSocketExceptionHandler.class);

    @MessageExceptionHandler(Exception.class)
    @SendToUser("/queue/errors")  // 구독 경로: /user/queue/errors
    public ErrorResponse handleGeneralException(Exception e) {
        log.error("WebSocket Error", e);
        return new ErrorResponse(false, "서버 오류 발생: " + e.getMessage(), "500");
    }
    
    
    @MessageExceptionHandler(BusinessException.class)
    @SendToUser("/queue/errors")
    public ErrorResponse handleBusinessException(BusinessException e) {
        log.error("Business Exception", e);
        return new ErrorResponse(false, e.getMessage(), e.getErrorCode());
    }
}
