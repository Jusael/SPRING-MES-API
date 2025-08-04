package com.example.JAVA_MES_API.api.handle;


import org.springframework.context.event.EventListener;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.example.JAVA_MES_API.api.dto.KafkaExecutionEvent;
import com.example.JAVA_MES_API.kafka.service.KafkaQueueService;

import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class KafkaQueueHandle {

	private final KafkaQueueService kafkaQueueService;
	
	@Async
	@EventListener
	@Retryable(
		    value = Exception.class,
		    maxAttempts = 3,
		    //backoff = @Backoff(delay = 300000) // 5분
		    backoff = @Backoff(delay = 200) // 
		)
	public void eventSendMessage(KafkaExecutionEvent kafkaExecutionEvent)
	{
		kafkaQueueService.sendKafkaMessage(kafkaExecutionEvent);
	}
}