package org.academy.jra;

import org.academy.jra.config.ConfigCache;
import org.academy.jra.config.SwaggerConfig;
import org.academy.jra.config.UtilsConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

@SpringBootApplication
//@EnableDiscoveryClient
@Import(value = {ConfigCache.class, SwaggerConfig.class, UtilsConfig.class})
public class ProfileCreditCardApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProfileCreditCardApplication.class, args);
	}
}
