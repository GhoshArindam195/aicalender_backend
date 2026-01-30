package com.holidays.alcalender_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.holidays.alcalender_backend", "com.holidays.alcalender_backend.mapper"})
public class AlcalenderBackendApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(AlcalenderBackendApplication.class, args);
	}

}
