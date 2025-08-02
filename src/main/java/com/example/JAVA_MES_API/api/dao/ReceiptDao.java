package com.example.JAVA_MES_API.api.dao;

import com.example.JAVA_MES_API.api.dto.BarcodeRequestDto;
import com.example.JAVA_MES_API.api.dto.FcmRequestDto;
import com.example.JAVA_MES_API.api.dto.FcmResponeseDto;
import com.example.JAVA_MES_API.api.dto.InventoryResponeseDto;
import com.example.JAVA_MES_API.api.dto.ItemResponeseDto;
import com.example.JAVA_MES_API.api.dto.JwtRequestDto;
import com.example.JAVA_MES_API.api.dto.LocationResponeDto;
import com.example.JAVA_MES_API.api.dto.LoginRequestDto;
import com.example.JAVA_MES_API.api.dto.LoginResponseDto;
import com.example.JAVA_MES_API.api.dto.SignRequestDto;
import com.example.JAVA_MES_API.api.dto.SignResponseDto;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;


public interface ReceiptDao {

	 ItemResponeseDto serachItemInfo(BarcodeRequestDto requestDto);
	 
	 LocationResponeDto searchLocationInfo(BarcodeRequestDto requestDto);
	 
	 List<InventoryResponeseDto> searchInventoryList(BarcodeRequestDto requestDto);
}
