package com.example.JAVA_MES_API.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "알람 요청 DTO")
public class AlarmsRequestDto {

	@Schema(description = "사용자 ID", example = "admin")
	private String userId;
	
	@Schema(description = "알람 ID", example = "1")
	private String appAlarmId;
	
	@Schema(description = "알람 상태", example = "Read")
	private String alarmStatus;
}
