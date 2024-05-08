package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RQuizService1Application {

	public static void main(String[] args) {
		SpringApplication.run(RQuizService1Application.class, args);
	}

}
