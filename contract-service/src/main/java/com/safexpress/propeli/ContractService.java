package com.safexpress.propeli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableCaching
@EnableAutoConfiguration
@EnableTransactionManagement
public class ContractService {

	public static void main(String[] args) {
		SpringApplication.run(ContractService.class, args);
	}

}
