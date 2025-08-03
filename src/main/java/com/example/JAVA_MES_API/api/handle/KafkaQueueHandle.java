package com.example.JAVA_MES_API.api.handle;


import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.context.event.EventListener;
import org.springframework.retry.annotation.Retryable;

import com.example.JAVA_MES_API.api.dto.KafkaExecutionEvent;
import com.example.JAVA_MES_API.api.dto.SignRequestDto;
import com.example.JAVA_MES_API.api.dto.SpExecutionEvent;
import com.example.JAVA_MES_API.api.dto.SpMappingDto;
import com.example.JAVA_MES_API.api.service.SpQueueService;
import com.example.JAVA_MES_API.kafka.service.KafkaQueueService;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;

import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class KafkaQueueHandle {

	private final KafkaQueueService kafkaQueueService;
	
	@Async
	@EventListener
	@Retryable(
		    value = Exception.class,
		    maxAttempts = 1,
		    //backoff = @Backoff(delay = 300000) // 5분
		    backoff = @Backoff(delay = 200) // 
		)
	public void eventSendMessage(KafkaExecutionEvent kafkaExecutionEvent)
	{
		kafkaQueueService.sendKafkaMessage(kafkaExecutionEvent);
	}
}