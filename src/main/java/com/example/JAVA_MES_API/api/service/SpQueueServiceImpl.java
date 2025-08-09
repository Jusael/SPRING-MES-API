package com.example.JAVA_MES_API.api.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.example.JAVA_MES_API.api.dto.SignRequestDto;
import com.example.JAVA_MES_API.api.dto.SpExecutionEvent;
import com.example.JAVA_MES_API.api.entity.SpExecutionQueue;
import com.example.JAVA_MES_API.api.entity.SpMapping;
import com.example.JAVA_MES_API.api.repository.SpExecutionQueueRepository;
import com.example.JAVA_MES_API.api.repository.SpMappingRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class SpQueueServiceImpl implements SpQueueService {

	private static final Logger log = LoggerFactory.getLogger(SpQueueService.class);

	private enum Status {
		Ready, Fail, Success, ReTrySuccess,
	}

	@PersistenceContext
	private EntityManager entityManager;
	private final SpExecutionQueueRepository spExecutionQueueRepository;
	private final SpMappingRepository spMappingRepository;
	private final ApplicationEventPublisher applicationEventPublisher;
	private final QueueStatusService queueStatusService;

	public SpQueueServiceImpl(SpExecutionQueueRepository spExecutionQueueRepository,
			SpMappingRepository spMappingRepository, ApplicationEventPublisher applicationEventPublisher,
			QueueStatusService queueStatusService) {
		this.spExecutionQueueRepository = spExecutionQueueRepository;
		this.spMappingRepository = spMappingRepository;
		this.applicationEventPublisher = applicationEventPublisher;
		this.queueStatusService = queueStatusService;
	}

	@Override
	public long createAndPublish(Object dto) {

		// 1. Que 삽입 및 QueId 발급
		long queId = this.insertSpExecutionQueue(dto);

		// 2. SP 실행 비동기 이벤트 처리
		applicationEventPublisher.publishEvent(new SpExecutionEvent(dto, queId));

		return queId;
	}

	private long insertSpExecutionQueue(Object paramDto) {

		SpExecutionQueue spExecutionQueue = new SpExecutionQueue();
		Map<String, Object> parameter = new HashMap<>();

		if (paramDto instanceof SignRequestDto signRequestDto) {
			SpMapping spMapping = spMappingRepository.findById(signRequestDto.getSignCd())
					.orElseThrow(() -> new IllegalArgumentException("SP Mapping not found for"));

			spExecutionQueue.setSpCd(spMapping.getSpCd());
			spExecutionQueue.setSpSchema(spMapping.getSpSchema());
			spExecutionQueue.setSpName(spMapping.getSpName());

			parameter.put(spMapping.getSpCd().equals("WORK_ORDER") ? "ORDER_NO" : "PACKING_ORDER_NO",
					signRequestDto.getKey1());

		}

		// buildParameter
		spExecutionQueue.setExecParams(this.buildParameter(parameter));
		spExecutionQueue.setCnt(0);
		spExecutionQueue.setStatus(Status.Ready.name());
		spExecutionQueue.setErrorMsg("");
		spExecutionQueue.setCreatedAt(LocalDateTime.now());

		long queId = spExecutionQueueRepository.save(spExecutionQueue).getQueId();
		return queId;
	}

	// TODO :
	// 1. obj와 연계할 정보를 찾는다.
	// 2. SP에 사용될 매개변수 문자열을 만든다.
	// 3. Q에 데이터를 삽입한다.
	// 4. SP를 실행한다.
	// 5. 성공유무를 저장한다.
	// 6. 3번까지 재실행 이후 더이상 진행하지않는다.
	@Override
	@Transactional
	public void callSP(SpExecutionEvent spExecutionEvent) {

		SpExecutionQueue spExecutionQueue = spExecutionQueueRepository.findById(spExecutionEvent.getQueId())
				.orElseThrow(() -> new IllegalArgumentException("Not found QueInfo"));

		String queStatus = spExecutionQueue.getStatus();

		if (!List.of(Status.Success.name(), Status.ReTrySuccess.name()).contains(queStatus)
				&& spExecutionQueue.getCnt() <= 3) {

			try {
				String excuteStr = "CALL" + " " + spExecutionQueue.getSpName() + "(" + spExecutionQueue.getExecParams()
						+ ")";

				entityManager.createNativeQuery(excuteStr).executeUpdate();

				spExecutionQueue.setStatus(
						queStatus.equals(Status.Fail.name()) ? Status.ReTrySuccess.name() : Status.Success.name());

			} catch (Exception e) {
				spExecutionQueue.setStatus(Status.Fail.name());
				spExecutionQueue.setErrorMsg(e.getMessage());
				spExecutionQueue.setCnt(spExecutionQueue.getCnt() + 1);
				throw e;
				
			} finally {
				queueStatusService.UpdateQueStatus(spExecutionQueue);
			}
		}
	}

	public String buildParameter(Map<String, Object> mapParameter) {
		if (mapParameter == null || mapParameter.isEmpty()) {
			return "";
		}

		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

		return mapParameter.values().stream().map(value -> {
			if (value == null) {
				return "NULL";
			} else if (value instanceof String) {
				return "'" + value + "'";
			} else if (value instanceof LocalDateTime) {
				return "'" + ((LocalDateTime) value).format(dateFormatter) + "'";
			} else if (value instanceof Number) {
				return value.toString();
			} else {
				return "'" + value.toString() + "'";
			}
		}).collect(Collectors.joining(", "));
	}
}