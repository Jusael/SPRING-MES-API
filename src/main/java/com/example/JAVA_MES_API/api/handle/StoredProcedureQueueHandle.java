package com.example.JAVA_MES_API.api.handle;

import org.springframework.context.event.EventListener;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.example.JAVA_MES_API.api.dto.SpExecutionEvent;
import com.example.JAVA_MES_API.api.service.SpQueueService;

import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class StoredProcedureQueueHandle {

	private final SpQueueService queueService;
	
	@Async
	@EventListener
	@Retryable(
		    value = Exception.class,
		    maxAttempts = 3,
		    //backoff = @Backoff(delay = 300000) // 5분
		    backoff = @Backoff(delay = 200) 
		)
	public void eventCallsp(SpExecutionEvent SpExecutionEvent)
	{
		queueService.callSP(SpExecutionEvent);
	}
}