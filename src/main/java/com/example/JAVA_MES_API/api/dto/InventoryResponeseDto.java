package com.example.JAVA_MES_API.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Schema(description = "적치 품목 응답 DTO")
public class InventoryResponeseDto {

	@Schema(description = "아이템 코드", example = "B0001")
	String itemCd;
	
	@Schema(description = "아이템 명칭", example = "청색2호 알루미늄레이크")
	String itemNm;
	
	@Schema(description = "Lot 번호", example = "230114")
	String lotNo;
	
	@Schema(description = "아이템 팩 바코드", example = "P000085423")
	String packBarCode;
	
	@Schema(description = "입고량", example = "1000.0000")
	double receiptQty;
	
	@Schema(description = "재고량", example = "970.0000")
	double remainQty;
}
