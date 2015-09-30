package com.op.service.portal;

import com.op.service.portal.settings.Settings;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableConfigurationProperties(Settings.class)
public class PortalServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(PortalServiceApplication.class, args);
	}
}