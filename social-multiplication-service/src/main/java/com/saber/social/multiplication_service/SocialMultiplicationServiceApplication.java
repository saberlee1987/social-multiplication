package com.saber.social.multiplication_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SocialMultiplicationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialMultiplicationServiceApplication.class, args);
	}

}
