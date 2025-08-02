package com.example.JAVA_MES_API.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.JAVA_MES_API.api.dao.ReceiptDao;
import com.example.JAVA_MES_API.api.dto.BarcodeRequestDto;
import com.example.JAVA_MES_API.api.dto.InventoryResponeseDto;
import com.example.JAVA_MES_API.api.dto.ItemResponeseDto;
import com.example.JAVA_MES_API.api.dto.LocationResponeDto;
import com.example.JAVA_MES_API.api.dto.SuccessDto;
import com.example.JAVA_MES_API.api.entity.Cell;
import com.example.JAVA_MES_API.api.entity.ReceiptPack;
import com.example.JAVA_MES_API.api.entity.ReceiptPackId;
import com.example.JAVA_MES_API.api.exception.BusinessException;
import com.example.JAVA_MES_API.api.repository.CellRepository;
import com.example.JAVA_MES_API.api.repository.ReceiptPackRepository;

import okhttp3.Response;

@Service
public class ReceiptServiceImpl implements ReceiptService{

	private final ReceiptDao receiptDao;
	private final CellRepository cellRepository;
	private final ReceiptPackRepository receiptPackRepository;
	
	public ReceiptServiceImpl(ReceiptDao receiptDao, CellRepository cellRepository, ReceiptPackRepository receiptPackRepository)
	{
		this.receiptDao = receiptDao;
		this.cellRepository = cellRepository;
		this.receiptPackRepository = receiptPackRepository;
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
	   Cell entity = cellRepository.findById(requestDto.getLocationBarocode()).orElseThrow(() -> new RuntimeException("Cell not found"));
	   LocationResponeDto responeDto = new LocationResponeDto();
	   
	   responeDto.setCellCd(entity.getCellCd());
	   responeDto.setCellNm(entity.getCellNm());
	   responeDto.setWareHouseCd(entity.getWareHouseCd());
	   responeDto.setWareHouseNm(entity.getWareHouseNm());
	   responeDto.setZoneCd(entity.getZoneCd());
	   responeDto.setZoneNm(entity.getZoneNm());
		
	   return responeDto;
	}
	
	//창고 재고 조회
	@Override
	public List<InventoryResponeseDto> searchInventoryList(BarcodeRequestDto requestDto)
	{
		List<InventoryResponeseDto> listResponse = new ArrayList();
		
		listResponse =  receiptDao.searchInventoryList(requestDto);
		
		return listResponse;
	}
	
	//피킹
	@Override
	public	SuccessDto updatePicking(BarcodeRequestDto requestDto)
	{
		ReceiptPack receiptPack  = receiptPackRepository.findByReceiptPackBarcodeNo(requestDto.getItemBarcode());
		receiptPack.setCellCd(null);
		receiptPack.setWarehouseCd(null);
		receiptPack.setZoneCd(null);
		
		receiptPackRepository.save(receiptPack);
		
		SuccessDto respone = new SuccessDto(); 
		respone.setSuccess(true);  
		
		return respone;
	}
	
	//적치
	@Override
	public SuccessDto updatePutAway(BarcodeRequestDto requestDto)
	{
		Cell cell = cellRepository.findById(requestDto.getLocationBarocode()).orElseThrow(() -> new RuntimeException("Not Found Cell Info"));
		
		ReceiptPack receiptPack  = receiptPackRepository.findByReceiptPackBarcodeNo(requestDto.getItemBarcode());
		
		receiptPack.setCellCd(cell.getCellCd());
		receiptPack.setWarehouseCd(cell.getWareHouseCd());
		receiptPack.setZoneCd(cell.getZoneCd());
		
		receiptPackRepository.save(receiptPack);
		
		SuccessDto respone = new SuccessDto(); 
		respone.setSuccess(true);  
		
		return respone;
	}

	
}
