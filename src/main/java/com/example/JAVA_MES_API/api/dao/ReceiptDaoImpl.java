package com.example.JAVA_MES_API.api.dao;

import java.io.Console;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
import com.example.JAVA_MES_API.api.exception.BusinessException;

@Repository
public class ReceiptDaoImpl implements ReceiptDao {

	private static final Logger log = LoggerFactory.getLogger(SignDaoImpl.class);

	@Autowired
	private SqlSession sqlSession;
	private String NAME_SPACE = "receiptMapper.";
	
	@Override
	public ItemResponeseDto serachItemInfo(BarcodeRequestDto requestDto) {
		
		ItemResponeseDto responeseDto =  sqlSession.selectOne(NAME_SPACE + "ItemSearchInfo", requestDto);
		
		return responeseDto;
	}

	@Override
	public LocationResponeDto searchLocationInfo(BarcodeRequestDto requestDto) {
		
		LocationResponeDto responeseDto =  sqlSession.selectOne(NAME_SPACE + "LocationSearchInfo", requestDto);
		
		return responeseDto;
	}

	@Override
	public InventoryResponeseDto searchInventoryList(BarcodeRequestDto requestDto) {

		InventoryResponeseDto responeseDto =  sqlSession.selectOne(NAME_SPACE + "InventorySearchInfo", requestDto);
		
		return responeseDto;
	}
}
