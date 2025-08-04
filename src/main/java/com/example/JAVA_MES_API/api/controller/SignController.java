package com.example.JAVA_MES_API.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.JAVA_MES_API.api.dto.PermissionResponseDto;
import com.example.JAVA_MES_API.api.dto.SignRequestDto;
import com.example.JAVA_MES_API.api.dto.SignResponseDto;
import com.example.JAVA_MES_API.api.service.SignService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/sign")
@Tag(name = "전자서명 API", description = "전자서명 정보를 관리합니다.")
public class SignController {

	private static final Logger log = LoggerFactory.getLogger(SignController.class);

	private final SignService signService;

	@Autowired
	public SignController(SignService signService) {
		this.signService = signService;
	}

	@GetMapping("/get-search-sign-info")
	@Operation(summary = "전자서명 조회", description = "전자서명 대상 정보를 조회합니다.")
	public SignResponseDto searchSignInfo(SignRequestDto signRequestDto) {

		return signService.searchSignInfo(signRequestDto);
	}
	
	@GetMapping("get-user-sign-info")
	@Operation(summary = "전자서명 권한 조회", description = "전자서명 권한을 조회합니다.")
	public PermissionResponseDto searchSignRoleInfo(SignRequestDto signRequestDto)
	{
		log.info(signRequestDto.getAppAlarmId());
		log.info(signRequestDto.getUserId());
		log.info(signRequestDto.getSignCd());
		
		return signService.searchSignRoleInfo(signRequestDto);
	}
	
	@PostMapping("/signIng")
	@Operation(summary = "전자서명 진행", description = "전자서명을 진행합니다.")
	public SignResponseDto updateSignResult(@RequestBody SignRequestDto signRequestDto)
	{
		return signService.updateSignResult(signRequestDto);
	}
}
