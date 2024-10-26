package com.fakeBankDetails.fakeBank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FakeBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(FakeBankApplication.class, args);
		System.out.println("Hello FAKE BANK");
	}

}
