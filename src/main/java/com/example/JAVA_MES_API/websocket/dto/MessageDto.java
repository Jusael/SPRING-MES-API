package com.example.JAVA_MES_API.websocket.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(description = "FCM 전송용 메시지 DTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MessageDto {

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

    @Schema(description = "생성일시", example = "2024-08-02 14:33:00")
    private String createTime;
}
