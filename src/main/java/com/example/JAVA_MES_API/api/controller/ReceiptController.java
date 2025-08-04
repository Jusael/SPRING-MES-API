
package com.example.JAVA_MES_API.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.JAVA_MES_API.api.dto.BarcodeRequestDto;
import com.example.JAVA_MES_API.api.dto.InventoryResponeseDto;
import com.example.JAVA_MES_API.api.dto.ItemResponeseDto;
import com.example.JAVA_MES_API.api.dto.LocationResponeDto;
import com.example.JAVA_MES_API.api.dto.SuccessDto;
import com.example.JAVA_MES_API.api.service.ReceiptService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/receipt")
@Tag(name = "물류 API", description = "물류 모듈에 대한 정보를 관리합니다.")
public class ReceiptController {

	private static final Logger log = LoggerFactory.getLogger(ReceiptController.class);

	private final ReceiptService receiptService;

	public ReceiptController(ReceiptService receiptService) {

		this.receiptService = receiptService;
	}

	@GetMapping("get-barcode-info")
	@Operation(summary = "품목 조회", description = "바코드 리딩한 품목 정보를 조회합니다.")
	public ItemResponeseDto serachItemInfo(BarcodeRequestDto requestDto) {

		return receiptService.searchItemInfo(requestDto);
	}

	// 장소조회
	@GetMapping("get-location-barcode-info")
	@Operation(summary = "적치 장소 조회", description = "적치 장소 정보를 조회합니다.")
	public LocationResponeDto searchLocationInfo(BarcodeRequestDto requestDto) {

		return receiptService.searchLocationInfo(requestDto);
	}

	// 창고 재고 조회
	@GetMapping("get-inventory-list")
	@Operation(summary = "적치 품목 조회", description = "장소에 적치된 품목 정보를 조회합니다.")
	public List<InventoryResponeseDto> searchInventoryList(BarcodeRequestDto requestDto) {

		return receiptService.searchInventoryList(requestDto);

	}

	@PostMapping("/product-picking")
	// 피킹
	@Operation(summary = "품목 피킹 처리", description = "장소에 적치된 품목을 피킹 합니다.")
	public SuccessDto updatePicking(BarcodeRequestDto requestDto) {

		return receiptService.updatePicking(requestDto);
	}

	@PostMapping("/product-put-away")
	@Operation(summary = "품목 적치 처리", description = "장소에 품목을 적 합니다.")
	public SuccessDto updatePutAway(BarcodeRequestDto requestDto) {

		return receiptService.updatePutAway(requestDto);
	}

}
