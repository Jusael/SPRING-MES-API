package com.example.JAVA_MES_API.websocket.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "fcm")
public class FcmPropertiesDto {
    private String projectId;
    private String serviceAccountPath;
}