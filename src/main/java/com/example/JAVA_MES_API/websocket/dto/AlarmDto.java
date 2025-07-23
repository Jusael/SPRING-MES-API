package com.example.JAVA_MES_API.websocket.dto;

import com.example.JAVA_MES_API.websocket.entity.AlarmWeb;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class AlarmDto {
	private int mesAlarmId;
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

	public static AlarmDto fromMessageDto(MessageDto messageDto) {
		return  AlarmDto.builder().mesAlarmId(messageDto.getMesAlarmId()).userId(messageDto.getUserId())
				.userNm(messageDto.getUserNm()).title(messageDto.getTitle()).content1(messageDto.getContent1())
				.content2(messageDto.getContent2()).content3(messageDto.getContent3())
				.content4(messageDto.getContent4()).content5(messageDto.getContent5()).signCd(messageDto.getSignCd())
				.signId(messageDto.getSignId()).key1(messageDto.getKey1()).key2(messageDto.getKey2())
				.key3(messageDto.getKey3()).key4(messageDto.getKey4()).key5(messageDto.getKey5())
				.createTime(messageDto.getCreateTime()).build();

	}
	
	public AlarmWeb toEntity() {
	    return AlarmWeb.builder()
	            .mesAlarmId(this.mesAlarmId)
	            .userId(this.userId)
	            .userNm(this.userNm)
	            .title(this.title)
	            .content1(this.content1)
	            .content2(this.content2)
	            .content3(this.content3)
	            .content4(this.content4)
	            .content5(this.content5)
	            .signCd(this.signCd)
	            .signId(this.signId)
	            .key1(this.key1)
	            .key2(this.key2)
	            .key3(this.key3)
	            .key4(this.key4)
	            .key5(this.key5)
	            .createTime(this.createTime)
	            .build();
	}

}
