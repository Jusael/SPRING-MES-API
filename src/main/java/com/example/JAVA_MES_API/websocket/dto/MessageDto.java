package com.example.JAVA_MES_API.websocket.dto;
import com.example.JAVA_MES_API.api.entity.Alarm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor  // ✅ Jackson 역직렬화를 위해 필요
@AllArgsConstructor // ✅ 편의상 사용
@ToString
public class MessageDto {
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
}
