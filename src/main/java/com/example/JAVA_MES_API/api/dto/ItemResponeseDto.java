package com.example.JAVA_MES_API.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter


@Schema(description = "품목 정보 응답 DTO")
public class ItemResponeseDto {
	
	@Schema(description = "아이템 명", example = "청색2호 알루미늄레이크")
	String itemNm;
	
	@Schema(description = "아이템 코드", example = "B0001")
	String itemCd;
	
	@Schema(description = "입고 Lot 번호", example = "230114")
	String receiptLotNo;
	
	@Schema(description = "입고 유효 기간", example = "2029-01-13")
	String receiptValidDate;
	
	@Schema(description = "팩 입고량", example = "1000.0000")
	String receiptPackQty;
	
	@Schema(description = "팩 재고량", example = "970.0000")
	String receiptPackRemainQty;
	
	@Schema(description = "입고 상태", example = "입고")
	String receiptStatus;
}
