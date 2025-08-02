package com.example.JAVA_MES_API.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "서명 진행 요청 DTO")
public class SignRequestDto {

	@Schema(description = "서명 코드", example = "PACKING_ORDER")
	private String signCd;

	@Schema(description = "서명 ID", example = "1")
	private String signId;

	@Schema(description = "사용자 ID", example = "admin")
	private String userId;

	@Schema(description = "APP 알람 ID", example = "1")
	private String appAlarmId;

	@Schema(description = "전자서명 키 코드 1", example = "P20210204-01")
	private String key1;
	
	@Schema(description = "전자서명 키 코드 2", example = "")
	private String key2;
	
	@Schema(description = "전자서명 키 코드 3", example = "")
	private String key3;
	
	@Schema(description = "전자서명 키 코드 4", example = "")
	private String key4;
	
	@Schema(description = "전자서명 키 코드 5", example = "")
	private String key5;
}
