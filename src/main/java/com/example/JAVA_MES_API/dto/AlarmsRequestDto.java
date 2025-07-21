package com.example.JAVA_MES_API.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlarmsRequestDto {

	private String UserId;
	private String AppAlarmId;
	private String AlarmStatus;
}
