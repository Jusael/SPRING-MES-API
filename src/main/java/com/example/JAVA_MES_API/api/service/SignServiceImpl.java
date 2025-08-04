package com.example.JAVA_MES_API.api.service;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.example.JAVA_MES_API.api.dao.SignDao;
import com.example.JAVA_MES_API.api.dto.PermissionResponseDto;
import com.example.JAVA_MES_API.api.dto.SignRequestDto;
import com.example.JAVA_MES_API.api.dto.SignResponseDto;
import com.example.JAVA_MES_API.api.entity.SignRecord;
import com.example.JAVA_MES_API.api.entity.User;
import com.example.JAVA_MES_API.api.exception.BusinessException;
import com.example.JAVA_MES_API.api.repository.SignRecordRepository;
import com.example.JAVA_MES_API.api.repository.UserRepository;
import com.example.JAVA_MES_API.kafka.service.KafkaQueueService;

@Service
public class SignServiceImpl implements SignService {

	private static final Logger log = LoggerFactory.getLogger(SignServiceImpl.class);

	private final SignRecordRepository signRecordRepository;
	private final UserRepository userRepository;
	private final ApplicationEventPublisher applicationEventPublisher;
	private final SpQueueService spQueueService;
	private final SignDao signDao;
	private final KafkaQueueService kafkaQueueService;
	

	@Autowired
	public SignServiceImpl(SignRecordRepository signRecordRepository, UserRepository userRepository,
			ApplicationEventPublisher applicationEventPublisher, SpQueueService spQueueService, SignDao signDao
			,KafkaQueueService kafkaQueueService
			) {
		this.signRecordRepository = signRecordRepository;
		this.userRepository = userRepository;
		this.applicationEventPublisher = applicationEventPublisher;
		this.spQueueService = spQueueService;
		this.signDao = signDao;
		this.kafkaQueueService = kafkaQueueService;
		
	}

	public SignResponseDto searchSignInfo(SignRequestDto signRequestDto) {

		SignResponseDto ResponseDto = signDao.searchSignInfo(signRequestDto);

		if (ResponseDto == null)
			throw new BusinessException("Sign 조회 실패", "NOT FIND SIGN");

		ResponseDto.setSuccess(true);

		return ResponseDto;
	}

	public PermissionResponseDto searchSignRoleInfo(SignRequestDto signRequestDto) {

		SignRecord signRecord = signRecordRepository.findBySignIdAndSignCdAndSignDetailUserId(
				signRequestDto.getSignId(), signRequestDto.getSignCd(), signRequestDto.getUserId());

		if (signRecord.getSignId() == null)
			throw new BusinessException("Sign 조회 실패", "NOT FIND SIGN");

		PermissionResponseDto  ResponseDto = new PermissionResponseDto();
		ResponseDto.setHasPermission(true);

		return ResponseDto;
	}

	public SignResponseDto updateSignResult(SignRequestDto signRequestDto) {

		User user = userRepository.findById(signRequestDto.getUserId())
				.orElseThrow(() -> new RuntimeException("User not found"));

		byte[] signImage = user.getSignImage();

		SignRecord signRecord = signRecordRepository.findById(signRequestDto.getSignId())
				.orElseThrow(() -> new RuntimeException("Sign not found"));

		signRecord.setSignImage(signImage);
		signRecord.setSignEmpCd(signRequestDto.getUserId());
		signRecord.setSignTime(LocalDateTime.now());

		signRecordRepository.save(signRecord);

		// NOTE : SP Q 비즈니스 로직 실행
		// 전자서명과 별개로 트랜잭션처리
		long spQueId = spQueueService.createAndPublish(signRequestDto);
		kafkaQueueService.createAndPublish(signRequestDto, spQueId);
		

		SignResponseDto responseDto = new SignResponseDto();
		responseDto.setSuccess(true);
		responseDto.setSignDetailNm(signRecord.getSignDetailNm());
		responseDto.setSignDetailUserId(signRecord.getSignDetailUserId());
		responseDto.setSignSignEmpCd(signRecord.getSignEmpCd());

		return responseDto;
	}
}
