package com.example.JAVA_MES_API.websocket.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component


@Schema(description = "FCM 프로젝트 매핑 DTO")
@ConfigurationProperties(prefix = "fcm")
public class FcmPropertiesDto {
	
	@Schema(description = "프로젝트 ID", example = "")
    private String projectId;
	
	@Schema(description = "접속 어카운트", example = "")
    private String serviceAccountPath;
}