package com.crm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloWorldApplication {	
    public static void main(String[] args) {
		SpringApplication.run(HelloWorldApplication.class, args);
	}
}
