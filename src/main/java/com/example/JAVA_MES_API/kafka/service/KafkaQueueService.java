package com.example.JAVA_MES_API.kafka.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.example.JAVA_MES_API.api.dto.KafkaExecutionEvent;
import com.example.JAVA_MES_API.api.dao.InterFaceDao;
import com.example.JAVA_MES_API.api.dto.*;
import com.example.JAVA_MES_API.kafka.*;
import com.example.JAVA_MES_API.api.service.QueueStatusService;
import com.example.JAVA_MES_API.kafka.repository.KafkaExecutionQueueRepository;
import com.example.JAVA_MES_API.kafka.repository.KafkaMappingRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.JAVA_MES_API.kafka.entity.KafkaExecutionQueue;
import com.example.JAVA_MES_API.kafka.entity.KafkaMapping;

import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder.Case;

@Service
public class KafkaQueueService {

	private static final Logger log = LoggerFactory.getLogger(KafkaQueueService.class);

	private enum Status {
		Ready, Fail, Success, ReTrySuccess,
	}

	private final KafkaMessageService kafkaMessageService;
	private final KafkaExecutionQueueRepository kafkaExecutionQueueRepository;
	private final KafkaMappingRepository kafkaMappingRepository;
	private final ApplicationEventPublisher applicationEventPublisher;
	private final InterFaceDao interFaceDao;

	public KafkaQueueService(KafkaMessageService kafkaMessageService,
			KafkaExecutionQueueRepository kafkaExecutionQueueRepository, KafkaMappingRepository kafkaMappingRepository,
			ApplicationEventPublisher applicationEventPublisher, InterFaceDao interFaceDao) {
		this.kafkaMessageService = kafkaMessageService;
		this.kafkaExecutionQueueRepository = kafkaExecutionQueueRepository;
		this.kafkaMappingRepository = kafkaMappingRepository;
		this.applicationEventPublisher = applicationEventPublisher;
		this.interFaceDao = interFaceDao;
	}

	public long createAndPublish(Object dto, long spQueId) {

		// 1. Que 삽입 및 QueId 발급
		long queId = this.insertKafakaExecutionQueue(dto, spQueId);

		// 2. kafka 실행 비동기 이벤트 처리
		applicationEventPublisher.publishEvent(new KafkaExecutionEvent(dto, queId, spQueId));

		return queId;
	}

	private long insertKafakaExecutionQueue(Object paramDto, Long spQueId) {

		KafkaExecutionQueue kafkaExecutionQueue = new KafkaExecutionQueue();
		String payload = "";
		Object dto = null;

		if (paramDto instanceof SignRequestDto signRequestDto) {
			KafkaMapping kafkaMapping = kafkaMappingRepository.findById(signRequestDto.getSignCd())
					.orElseThrow(() -> new IllegalArgumentException("SP Mapping not found for"));

			kafkaExecutionQueue.setSpQueId(spQueId);
			kafkaExecutionQueue.setTopic(signRequestDto.getSignCd());

			switch (signRequestDto.getSignCd()) {
			case "WORK_ORDER": {
				dto = interFaceDao.searchOrderIfInfo(signRequestDto.getSignId());

				break;
			}
			case "PACKING_ORDER": {

				dto = interFaceDao.searchPakcingOrderIfInfo(signRequestDto.getSignId());

				break;
			}
			}
		}

		payload = convertToJson(dto);
		kafkaExecutionQueue.setPayload(payload);
		kafkaExecutionQueue.setCnt(0);
		kafkaExecutionQueue.setStatus(Status.Ready.name());
		kafkaExecutionQueue.setErrorMsg("");
		kafkaExecutionQueue.setCreatedAt(LocalDateTime.now());

		long queId = kafkaExecutionQueueRepository.save(kafkaExecutionQueue).getQueId();
		return queId;
	}

	@Transactional
	public void sendKafkaMessage(KafkaExecutionEvent kafkaExecutionEvent) {
		KafkaExecutionQueue queue = kafkaExecutionQueueRepository.findById(kafkaExecutionEvent.getQueId())
				.orElseThrow(() -> new IllegalArgumentException("Not found QueInfo"));

		boolean success = kafkaMessageService.publish(queue.getTopic(), queue.getPayload());

		if (success) {
			queue.setStatus("SUCCESS");
		} else {
			queue.setStatus("FAIL");
			queue.setCnt(queue.getCnt() + 1);
			queue.setErrorMsg("Kafka 전송 실패"); // 필요시 예외 메시지도 넘기도록 개선 가능
		}

		kafkaExecutionQueueRepository.save(queue);
	}

	public String convertToJson(Object dto) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();

			return objectMapper.writeValueAsString(dto);

		} catch (JsonProcessingException e) {
			throw new RuntimeException("JSON 직렬화 실패: " + e.getMessage(), e);
		}
	}
}