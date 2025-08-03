package com.example.JAVA_MES_API.kafka.service;

public interface KafkaMessageService {

	public boolean publish(String topic, String payload);
}
