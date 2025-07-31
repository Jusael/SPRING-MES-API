package com.example.JAVA_MES_API.api.service;

import java.util.List;

import com.example.JAVA_MES_API.api.dto.BarcodeRequestDto;
import com.example.JAVA_MES_API.api.dto.InventoryResponeseDto;
import com.example.JAVA_MES_API.api.dto.ItemResponeseDto;
import com.example.JAVA_MES_API.api.dto.LocationResponeDto;
import com.example.JAVA_MES_API.api.dto.SuccessDto;

public interface ReceiptService {

	//품목조회
	ItemResponeseDto searchItemInfo(BarcodeRequestDto requestDto);
	//장소조회
	LocationResponeDto searchLocationInfo(BarcodeRequestDto requestDto);
	//창고 재고 조회
	List<InventoryResponeseDto> searchInventoryList(BarcodeRequestDto requestDto);
	//피킹
	SuccessDto updatePicking(BarcodeRequestDto requestDto);
	//적치
	SuccessDto updatePutAway(BarcodeRequestDto requestDto);

}
