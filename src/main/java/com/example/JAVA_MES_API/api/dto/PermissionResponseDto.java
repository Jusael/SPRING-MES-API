package com.example.JAVA_MES_API.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Schema(description = "권한 체크 응답 DTO")
public class PermissionResponseDto {
	
	@Schema(description = "Exists 여부", example = "true")
    private boolean hasPermission;
}
