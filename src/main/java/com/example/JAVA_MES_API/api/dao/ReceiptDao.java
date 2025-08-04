package com.example.JAVA_MES_API.api.dao;

import java.util.List;

import com.example.JAVA_MES_API.api.dto.BarcodeRequestDto;
import com.example.JAVA_MES_API.api.dto.InventoryResponeseDto;
import com.example.JAVA_MES_API.api.dto.ItemResponeseDto;
import com.example.JAVA_MES_API.api.dto.LocationResponeDto;


public interface ReceiptDao {

	 ItemResponeseDto serachItemInfo(BarcodeRequestDto requestDto);
	 
	 LocationResponeDto searchLocationInfo(BarcodeRequestDto requestDto);
	 
	 List<InventoryResponeseDto> searchInventoryList(BarcodeRequestDto requestDto);
}
