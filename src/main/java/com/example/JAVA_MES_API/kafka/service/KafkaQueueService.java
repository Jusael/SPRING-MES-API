package com.example.JAVA_MES_API.kafka.service;

import com.example.JAVA_MES_API.api.dto.KafkaExecutionEvent;

public interface KafkaQueueService {

	long createAndPublish(Object dto, long spQueId);
	
	void sendKafkaMessage(KafkaExecutionEvent kafkaExecutionEvent);
}
