package com.example.JAVA_MES_API.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "바코드 요청 DTO")
public class BarcodeRequestDto {

	@Schema(description = "아이템 바코드", example = "P000085424")
	String itemBarcode;
	
	@Schema(description = "장소 바코드", example = "C12001B004")
	String locationBarocode;
}
