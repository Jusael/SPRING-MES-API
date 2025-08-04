
package com.example.JAVA_MES_API.api.dao;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.JAVA_MES_API.api.dto.SignRequestDto;
import com.example.JAVA_MES_API.api.dto.SignResponseDto;

@Repository
public class SignDaoImpl implements SignDao {

	private static final Logger log = LoggerFactory.getLogger(SignDaoImpl.class);

	@Autowired
	private SqlSession sqlSession;
	private String NAME_SPACE = "signMapper.";
	
	
	@Override
	public SignResponseDto searchSignInfo(SignRequestDto signRequestDto) {
		
		 SignResponseDto responseDto = sqlSession.selectOne(NAME_SPACE + "signSearchInfo", signRequestDto);
		 
		 return responseDto;
	}
}
