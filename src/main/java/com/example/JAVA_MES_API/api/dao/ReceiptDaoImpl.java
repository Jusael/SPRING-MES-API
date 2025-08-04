package com.example.JAVA_MES_API.api.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.JAVA_MES_API.api.dto.BarcodeRequestDto;
import com.example.JAVA_MES_API.api.dto.InventoryResponeseDto;
import com.example.JAVA_MES_API.api.dto.ItemResponeseDto;
import com.example.JAVA_MES_API.api.dto.LocationResponeDto;

@Repository
public class ReceiptDaoImpl implements ReceiptDao {

	private static final Logger log = LoggerFactory.getLogger(ReceiptDaoImpl.class);

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
	public  List<InventoryResponeseDto> searchInventoryList(BarcodeRequestDto requestDto) {

		List<InventoryResponeseDto>  responeseDto =  sqlSession.selectList(NAME_SPACE + "InventorySearchInfo", requestDto);
		
		return responeseDto;
	}
}
