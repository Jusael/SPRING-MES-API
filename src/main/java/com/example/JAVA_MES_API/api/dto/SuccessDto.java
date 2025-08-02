package com.example.JAVA_MES_API.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter


@Schema(description = "성공 응답 DTO")
public class SuccessDto {

	@Schema(description = "성공 여부", example = "true")
	boolean success;
}
