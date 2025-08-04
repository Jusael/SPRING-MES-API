package com.example.JAVA_MES_API.api.dao;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.JAVA_MES_API.kafka.dto.IfOrderDto;
import com.example.JAVA_MES_API.kafka.dto.IfPackingOrderDto;


@Repository
public class InterFaceDaoImpl implements InterFaceDao {

	private static final Logger log = LoggerFactory.getLogger(ReceiptDaoImpl.class);

	@Autowired
	private SqlSession sqlSession;
	private String NAME_SPACE = "interFaceMapper.";
	
	@Override
	public IfOrderDto searchOrderIfInfo(int signId) {
		
		IfOrderDto responeseDto =  sqlSession.selectOne(NAME_SPACE + "IfOrder", signId);
		
		return responeseDto;
	}
	
	@Override
	public IfPackingOrderDto searchPakcingOrderIfInfo(int signId) {
		
		IfPackingOrderDto responeseDto =  sqlSession.selectOne(NAME_SPACE + "IfPackingOrder", signId);
		
		return responeseDto;
	}
}
