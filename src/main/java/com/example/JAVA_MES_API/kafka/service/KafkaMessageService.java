package com.example.JAVA_MES_API.kafka.service;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class KafkaMessageService {

	private static final Logger log = LoggerFactory.getLogger(KafkaMessageService.class);
	
    private final KafkaTemplate<String, String> kafkaTemplate;

    public boolean publish(String topic, String payload) {
        try {
            kafkaTemplate.send(topic, payload);
            log.info("✅ Kafka 메시지 전송 성공");
            return true;
        } catch (Exception e) {
            log.info("❌ Kafka 메시지 전송 실패: {}", e.getMessage());
            return false;
        }
    }
}
