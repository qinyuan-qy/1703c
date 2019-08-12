package com.qinyuan.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.qinyuan")
public class QinyuanRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(QinyuanRedisApplication.class, args);
	}

}
