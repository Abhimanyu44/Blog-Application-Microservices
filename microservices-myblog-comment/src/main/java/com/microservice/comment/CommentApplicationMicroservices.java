package com.microservice.comment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CommentApplicationMicroservices {

	public static void main(String[] args) {
		SpringApplication.run(CommentApplicationMicroservices.class, args);
	}

}
