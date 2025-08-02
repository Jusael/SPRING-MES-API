package com.example.JAVA_MES_API.api.dao;

import com.example.JAVA_MES_API.api.dto.IfOrderDto;
import com.example.JAVA_MES_API.api.dto.IfPackingOrderDto;

public interface InterFaceDao {

	IfOrderDto searchOrderIfInfo(int signId);
	IfPackingOrderDto searchPakcingOrderIfInfo(int signId);
}
