package com.niit.UserRegisterService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UserRegisterServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserRegisterServiceApplication.class, args);
	}

}
