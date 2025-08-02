package com.example.JAVA_MES_API.websocket.dto;

import com.example.JAVA_MES_API.websocket.entity.AlarmWeb;

import io.swagger.v3.oas.annotations.media.Schema;
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


@Schema(description = "알람 전송 DTO")
public class AlarmDto {
	
	@Schema(description = "MES 알람 ID", example = "8")
	private int mesAlarmId;
	
	@Schema(description = "사용자 ID", example = "admin")
	private String userId;
	
	@Schema(description = "사용자 명", example = "관리자")
	private String userNm;
	
	@Schema(description = "제목", example = "포장지시 전자서명 요청")
	private String title;
	
	@Schema(description = "내용1", example = "품목명 : 라이카")
	private String content1;
	
	@Schema(description = "내용2", example = "제조번호 : LX41")
	private String content2;
	
	@Schema(description = "내용3", example = "서명의미 : 제조부서책임자")
	private String content3;
	
	@Schema(description = "내용4", example = "")
	private String content4;
	
	@Schema(description = "내용5", example = "")
	private String content5;
	
	@Schema(description = "서명 코드", example = "PACKING_ORDER")
	private String signCd;
	
	@Schema(description = "서명 ID", example = "1")
	private String signId;
	
	@Schema(description = "서명 Key1", example = "TEST210127-02")
	private String key1;
	
	@Schema(description = "서명 Key2", example = "")
	private String key2;
	
	@Schema(description = "서명 Key3", example = "")
	private String key3;
	
	@Schema(description = "서명 Key4", example = "")
	private String key4;
	
	@Schema(description = "서명 Key5", example = "")
	private String key5;
	
	@Schema(description = "서명 Key6", example = "")
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
