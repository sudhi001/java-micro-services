package com.op.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import reactor.Environment;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableZuulProxy
@EnableCircuitBreaker
public class ApiGatewayApplication {
	@Bean
	public Environment env() {
		return Environment.initializeIfEmpty();
	}

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}
}