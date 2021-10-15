package org.academy.jra;

import org.academy.jra.config.SwaggerConfig;
import org.academy.jra.config.UtilsConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@Import({SwaggerConfig.class, UtilsConfig.class})
public class SearchNearAtmApplication {

	public static void main(String[] args) {
		SpringApplication.run(SearchNearAtmApplication.class, args);
	}
}
