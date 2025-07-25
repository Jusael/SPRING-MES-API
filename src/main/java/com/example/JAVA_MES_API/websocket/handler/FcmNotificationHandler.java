package com.example.JAVA_MES_API.websocket.handler;

import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.example.JAVA_MES_API.websocket.entity.FcmSavedEvent;
import com.example.JAVA_MES_API.websocket.service.FcmService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FcmNotificationHandler {

	private final FcmService fcmService;

	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void eventPushFcm(FcmSavedEvent event) {

		fcmService.pushNotification(event.getAlarm());
	}
}
