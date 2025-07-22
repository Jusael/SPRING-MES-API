package com.example.JAVA_MES_API.api.dto;

import com.example.JAVA_MES_API.api.entity.Alarm;
import com.example.JAVA_MES_API.api.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AlarmsResponseDto {

	private int appAlarmId;
	private int mesAlarmId;
	private String fcmToken;
	private String userId;
	private String userNm;
	private String title;
	private String content1;
	private String content2;
	private String content3;
	private String content4;
	private String content5;
	private String signCd;
	private String signId;
	private String key1;
	private String key2;
	private String key3;
	private String key4;
	private String key5;
	private String createTime;

	public static AlarmsResponseDto fromEntity(Alarm alarm) {
		return new AlarmsResponseDto(alarm.getAppAlarmId(), alarm.getMesAlarmId(), null, alarm.getUserId(),
				alarm.getUserNm(), alarm.getTitle(), alarm.getContent1(), alarm.getContent2(), alarm.getContent3(),
				alarm.getContent4(), alarm.getContent5(), alarm.getSignCd(), alarm.getSignId(), alarm.getKey1(),
				alarm.getKey2(), alarm.getKey3(), alarm.getKey4(), alarm.getKey5(), alarm.getCreateTime()

		);
	}
}
