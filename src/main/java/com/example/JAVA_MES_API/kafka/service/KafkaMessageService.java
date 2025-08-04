package com.example.JAVA_MES_API.kafka.service;

public interface KafkaMessageService {

	 boolean publish(String topic, String queId, String payload);
}
