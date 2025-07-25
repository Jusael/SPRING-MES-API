package com.example.JAVA_MES_API.api.handle;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.context.event.EventListener;
import org.springframework.retry.annotation.Retryable;

import com.example.JAVA_MES_API.api.dto.SignRequestDto;
import com.example.JAVA_MES_API.api.dto.SpExecutionEvent;
import com.example.JAVA_MES_API.api.dto.SpMappingDto;
import com.example.JAVA_MES_API.manager.StoredProcedureQueueManager;
import com.example.JAVA_MES_API.websocket.entity.FcmSavedEvent;
import com.example.JAVA_MES_API.websocket.service.FcmService;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;

import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class StoredProcedureQueueHandle {

	private final StoredProcedureQueueManager storedProcedureQueueManager;
	
	@Async
	@EventListener
	@Retryable(
		    value = Exception.class,
		    maxAttempts = 3,
		    backoff = @Backoff(delay = 300000) // 5분
		)
	public void eventCallsp(SpExecutionEvent SpExecutionEvent)
	{
		storedProcedureQueueManager.callSP(SpExecutionEvent);
	}
}