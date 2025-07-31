package com.example.JAVA_MES_API.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryResponeseDto {

	String itemCd;
	String itemNm;
	String lotNo;
	String packBarCode;
	double receiptQty;
	double remainQty;
}
