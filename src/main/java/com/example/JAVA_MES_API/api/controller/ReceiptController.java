
package com.example.JAVA_MES_API.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.JAVA_MES_API.api.dao.UserDaoImpl;
import com.example.JAVA_MES_API.api.dto.BarcodeRequestDto;
import com.example.JAVA_MES_API.api.dto.InventoryResponeseDto;
import com.example.JAVA_MES_API.api.dto.ItemResponeseDto;
import com.example.JAVA_MES_API.api.dto.LocationResponeDto;
import com.example.JAVA_MES_API.api.dto.PermissionResponseDto;
import com.example.JAVA_MES_API.api.dto.SignRequestDto;
import com.example.JAVA_MES_API.api.dto.SignResponseDto;
import com.example.JAVA_MES_API.api.dto.SuccessDto;
import com.example.JAVA_MES_API.api.service.ReceiptService;
import com.example.JAVA_MES_API.api.service.SignService;

@RestController
@RequestMapping("/api/receipt")
public class ReceiptController {

	private static final Logger log = LoggerFactory.getLogger(ReceiptController.class);
	
	private final  ReceiptService receiptService;
	
	public ReceiptController(ReceiptService receiptService) {
		
		this.receiptService = receiptService;
	}

	@GetMapping("get-barcode-info")
	public ItemResponeseDto serachItemInfo(BarcodeRequestDto requestDto) {
		
		return receiptService.searchItemInfo(requestDto);
	}

	// 장소조회
	@GetMapping("get-location-barcode-info")
	public LocationResponeDto searchLocationInfo(BarcodeRequestDto requestDto) {
		
		return receiptService.searchLocationInfo(requestDto);
	}

	// 창고 재고 조회
	@GetMapping("get-inventory-list")
	public 	List<InventoryResponeseDto> searchInventoryList(BarcodeRequestDto requestDto) {

		return receiptService.searchInventoryList(requestDto);
		
		
	}

	@PostMapping("/product-picking")
	// 피킹
	public SuccessDto updatePicking(BarcodeRequestDto requestDto) {


		return receiptService.updatePicking(requestDto);
	}

	@PostMapping("/product-put-away")
	// 적치
	public SuccessDto updatePutAway(BarcodeRequestDto requestDto) {
		
		return receiptService.updatePutAway(requestDto);
	}

}
