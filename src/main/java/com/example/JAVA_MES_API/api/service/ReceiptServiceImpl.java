package com.example.JAVA_MES_API.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.JAVA_MES_API.api.dao.ReceiptDao;
import com.example.JAVA_MES_API.api.dto.BarcodeRequestDto;
import com.example.JAVA_MES_API.api.dto.InventoryResponeseDto;
import com.example.JAVA_MES_API.api.dto.ItemResponeseDto;
import com.example.JAVA_MES_API.api.dto.LocationResponeDto;
import com.example.JAVA_MES_API.api.dto.SuccessDto;
import com.example.JAVA_MES_API.api.entity.Cell;
import com.example.JAVA_MES_API.api.exception.BusinessException;
import com.example.JAVA_MES_API.api.repository.CellRepository;

@Service
public class ReceiptServiceImpl implements ReceiptService{

	private final ReceiptDao receiptDao;
	private final CellRepository cellRepository;
	
	public ReceiptServiceImpl(ReceiptDao receiptDao, CellRepository cellRepository)
	{
		this.receiptDao = receiptDao;
		this.cellRepository = cellRepository;
	}
	
	//품목조회
	@Override
	public ItemResponeseDto searchItemInfo(BarcodeRequestDto requestDto)
	{
		ItemResponeseDto responeseDto = receiptDao.serachItemInfo(requestDto);
		
		if(responeseDto == null)
			throw new BusinessException("품목 정보 조회 실패", "NOT FIND ITEM");
		
		return responeseDto;
	}
	
	//장소조회
	@Override
	public LocationResponeDto searchLocationInfo(BarcodeRequestDto requestDto)
	{
	   Cell entityCell = cellRepository.findById(requestDto.getBarcode()).orElseThrow(() -> new RuntimeException("User not found"));
		return null;
	}
	
	//창고 재고 조회
	@Override
	public List<InventoryResponeseDto> searchInventoryList(BarcodeRequestDto requestDto)
	{
		List<InventoryResponeseDto> aDtos = null;
		return aDtos;
	}
	
	//피킹
	@Override
	public	SuccessDto updatePicking(BarcodeRequestDto requestDto)
	{
		return null;
	}
	
	//적치
	@Override
	public SuccessDto updatePutAway(BarcodeRequestDto requestDto)
	{
		return null;
	}

	
}
