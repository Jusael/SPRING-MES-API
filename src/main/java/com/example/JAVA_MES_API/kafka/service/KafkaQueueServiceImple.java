package com.example.JAVA_MES_API.kafka.service;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.JAVA_MES_API.api.dao.InterFaceDao;
import com.example.JAVA_MES_API.api.dto.KafkaExecutionEvent;
import com.example.JAVA_MES_API.api.dto.SignRequestDto;
import com.example.JAVA_MES_API.kafka.entity.KafkaExecutionQueue;
import com.example.JAVA_MES_API.kafka.entity.KafkaMapping;
import com.example.JAVA_MES_API.kafka.repository.KafkaExecutionQueueRepository;
import com.example.JAVA_MES_API.kafka.repository.KafkaMappingRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class KafkaQueueServiceImple implements KafkaQueueService {

	private static final Logger log = LoggerFactory.getLogger(KafkaQueueServiceImple.class);

	private enum Status {
		Ready, Fail, Success, ReTrySuccess,
	}

	private final KafkaMessageService kafkaMessageService;
	private final KafkaExecutionQueueRepository kafkaExecutionQueueRepository;
	private final KafkaMappingRepository kafkaMappingRepository;
	private final ApplicationEventPublisher applicationEventPublisher;
	private final InterFaceDao interFaceDao;

	public KafkaQueueServiceImple(KafkaMessageService kafkaMessageService,
			KafkaExecutionQueueRepository kafkaExecutionQueueRepository, KafkaMappingRepository kafkaMappingRepository,
			ApplicationEventPublisher applicationEventPublisher, InterFaceDao interFaceDao) {
		this.kafkaMessageService = kafkaMessageService;
		this.kafkaExecutionQueueRepository = kafkaExecutionQueueRepository;
		this.kafkaMappingRepository = kafkaMappingRepository;
		this.applicationEventPublisher = applicationEventPublisher;
		this.interFaceDao = interFaceDao;
	}

	@Override
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

			switch (signRequestDto.getSignCd()) {
			case "WORK_ORDER":

				kafkaExecutionQueue.setTopic(kafkaMapping.getTopic());

				dto = interFaceDao.searchOrderIfInfo(signRequestDto.getSignId());

				break;

			case "PACKING_ORDER":

				kafkaExecutionQueue.setTopic(kafkaMapping.getTopic());

				dto = interFaceDao.searchPakcingOrderIfInfo(signRequestDto.getSignId());

				break;

			default:
			    throw new IllegalArgumentException("Unsupported signCd: " + signRequestDto.getSignCd());
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

	@Override
	@Transactional
	public void sendKafkaMessage(KafkaExecutionEvent kafkaExecutionEvent) {

		KafkaExecutionQueue queue = kafkaExecutionQueueRepository.findById(kafkaExecutionEvent.getQueId())
				.orElseThrow(() -> new IllegalArgumentException("Not found QueInfo"));

		try {

			boolean success = kafkaMessageService.publish(queue.getTopic(), queue.getQueId().toString(),
					queue.getPayload());

			queue.setStatus(Status.Success.name());

		} catch (Exception e) {

			queue.setStatus(Status.Fail.name());
			queue.setCnt(queue.getCnt() + 1);
			queue.setErrorMsg("Kafka 전송 실패");

		} finally {
			kafkaExecutionQueueRepository.save(queue);
		}
	}

	private String convertToJson(Object dto) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();

			return objectMapper.writeValueAsString(dto);

		} catch (JsonProcessingException e) {
			throw new RuntimeException("JSON 직렬화 실패: " + e.getMessage(), e);
		}
	}
}