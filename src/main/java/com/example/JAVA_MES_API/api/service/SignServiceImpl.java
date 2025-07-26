package com.example.JAVA_MES_API.api.service;

import java.time.LocalDateTime;

import javax.management.RuntimeErrorException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.example.JAVA_MES_API.api.dto.SignRequestDto;
import com.example.JAVA_MES_API.api.dto.SignResponseDto;
import com.example.JAVA_MES_API.api.dto.SpMappingDto;
import com.example.JAVA_MES_API.api.dto.SpExecutionEvent;

import com.example.JAVA_MES_API.api.entity.SignRecord;
import com.example.JAVA_MES_API.api.entity.User;
import com.example.JAVA_MES_API.api.exception.BusinessException;
import com.example.JAVA_MES_API.api.repository.SignRecordRepository;
import com.example.JAVA_MES_API.api.repository.UserRepository;
import com.example.JAVA_MES_API.websocket.entity.FcmSavedEvent;
import com.google.api.client.util.DateTime;

@Service
public class SignServiceImpl implements SignService {

	private static final Logger log = LoggerFactory.getLogger(SignServiceImpl.class);

	private final SignRecordRepository signRecordRepository;
	private final UserRepository userRepository;
	private final ApplicationEventPublisher applicationEventPublisher;
	private final QueueService queueService;
	
	@Autowired
	public SignServiceImpl(SignRecordRepository signRecordRepository
			, UserRepository userRepository
			, ApplicationEventPublisher applicationEventPublisher
			, QueueService queueService) {
		this.signRecordRepository = signRecordRepository;
		this.userRepository = userRepository;
		this.applicationEventPublisher = applicationEventPublisher;
		this.queueService = queueService;
	}

	public SignResponseDto searchSignInfo(SignRequestDto signRequestDto) {

		SignResponseDto ResponseDto = signRecordRepository
				.findSignInfoNative(Integer.parseInt(signRequestDto.getSignId()), signRequestDto.getSignCd());

		if (ResponseDto == null)
			throw new BusinessException("Sign 조회 실패", "NOT FIND SIGN");

		ResponseDto.setSuccess(true);
		return ResponseDto;
	}

	public SignResponseDto searchSignRoleInfo(SignRequestDto signRequestDto) {

		SignResponseDto ResponseDto = signRecordRepository.findBySignIdAndSignCdandSignDetailUserId(
				Integer.parseInt(signRequestDto.getSignId()), signRequestDto.getSignCd(), signRequestDto.getUserId());

		if (ResponseDto == null)
			throw new BusinessException("Sign 조회 실패", "NOT FIND SIGN");

		ResponseDto.setSuccess(true);
		return ResponseDto;
	}

	public SignResponseDto updateSignResult(SignRequestDto signRequestDto) {

		User user = userRepository.findById(signRequestDto.getUserId())
				.orElseThrow(() -> new RuntimeException("User not found"));

		byte[] signImage = user.getSignImage();

		SignRecord signRecord = signRecordRepository.findById(Integer.parseInt(signRequestDto.getSignId()))
				.orElseThrow(() -> new RuntimeException("Sign not found"));

		signRecord.setSignImage(signImage);
		signRecord.setSignEmpCd(signRequestDto.getUserId());
		signRecord.setSignTime(LocalDateTime.now());

		signRecordRepository.save(signRecord);

		// NOTE : SP 비즈니스 로직 실행
		// 전자서명과 별개로 트랜잭션처리
		queueService.createAndPublish(signRequestDto);
		
		SignResponseDto responseDto = new SignResponseDto();
		responseDto.setSuccess(true);

		return responseDto;
	}
}
