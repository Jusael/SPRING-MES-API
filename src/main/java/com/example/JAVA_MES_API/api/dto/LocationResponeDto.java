package com.example.JAVA_MES_API.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Setter;

@Setter


@Schema(description = "장소 정보 응답 DTO")
public class LocationResponeDto {
	
	@Schema(description = "창고코드", example = "WH-001")
	String wareHouseCd;
	
	@Schema(description = "창고명", example = "원부자재 보관 창고")
	String wareHouseNm;
	
	@Schema(description = "구역코드", example = "Z1-2001")
	String zoneCd;
	
	@Schema(description = "구역명", example = "적합품목 적치")
	String zoneNm;
	
	@Schema(description = "셀코드", example = "C12001B000")
	String cellCd;
	
	@Schema(description = "셀명", example = "B000")
	String cellNm;
}
