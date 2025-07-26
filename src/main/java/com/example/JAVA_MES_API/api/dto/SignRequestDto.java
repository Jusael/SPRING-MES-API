package com.example.JAVA_MES_API.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignRequestDto {
	private String signCd;
	private String signId;
	private String userId;
	private String appAlarmId;
	private String mesAlarmId;
	private String key1;
	private String key2;
	private String key3;
	private String key4;
	private String key5;
}
