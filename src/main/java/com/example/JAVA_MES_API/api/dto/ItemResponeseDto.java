package com.example.JAVA_MES_API.api.dto;

import lombok.Getter;

@Getter
public class ItemResponeseDto {
	String itemNm;
	String itemCd;
	String receiptLotNo;
	String receiptValidDate;
	String receiptPackQty;
	String receiptPackRemainQty;
	String receiptStatus;
}
