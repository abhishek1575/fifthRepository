package com.ceinsys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages= "com.ceinsys.repo")
@EntityScan(basePackages="com.ceinsys.model")


public class HardwareAssetManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(HardwareAssetManagementApplication.class, args);
	}

}
