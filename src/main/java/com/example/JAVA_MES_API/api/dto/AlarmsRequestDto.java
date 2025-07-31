package com.example.JAVA_MES_API.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlarmsRequestDto {

	private String userId;
	private String appAlarmId;
	private String alarmStatus;
}
