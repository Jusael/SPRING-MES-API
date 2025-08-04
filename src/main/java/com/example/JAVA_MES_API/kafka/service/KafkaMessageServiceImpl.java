package com.example.JAVA_MES_API.kafka.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class KafkaMessageServiceImpl implements KafkaMessageService {

	private static final Logger log = LoggerFactory.getLogger(KafkaMessageService.class);
	
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public boolean publish(String topic, String queId, String payload) {
        try {
            kafkaTemplate.send(topic,queId, payload);
            log.info("✅ Kafka 메시지 전송 성공");
            return true;
        } catch (Exception e) {
            log.info("❌ Kafka 메시지 전송 실패: {}", e.getMessage());
            return false;
        }
    }
}
