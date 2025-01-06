package com.food_microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class FoodMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodMicroserviceApplication.class, args);
	}

}
