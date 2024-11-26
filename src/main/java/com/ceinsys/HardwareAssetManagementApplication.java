package com.ceinsys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.admin.SpringApplicationAdminJmxAutoConfiguration;

@SpringBootApplication

public class HardwareAssetManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(HardwareAssetManagementApplication.class, args);
	}

}
