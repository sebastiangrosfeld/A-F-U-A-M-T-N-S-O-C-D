package com.study.carDealershipsServer;

import org.springframework.boot.SpringApplication;

public class TestCarDealershipsServerApplication {

	public static void main(String[] args) {
		SpringApplication.from(CarDealershipsServerApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
