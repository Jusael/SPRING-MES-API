package com.example.JAVA_MES_API;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@SpringBootApplication

public class JavaMesApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaMesApiApplication.class, args);
	}

}
