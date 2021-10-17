package org.academy.jra.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Clase de configuración para la
 * documentación con Swagger.
 * 
 * @author Joel Rubio
 *
 */
@Configuration
public class SwaggerConfig {

	@Bean
	public Docket docket() {
		
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("org.academy.jra"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());
	}
	
	@Bean
	public ApiInfo apiInfo() {
		
		return new ApiInfo(
				"Search Bank Entity API",
				"API que obtiene la locación de un ATM o una sucursal bancaria",
				"API",
				"Terms of Service",
				new Contact("Joel Rubio", "www.example.com", "joelrubio021@gmail.com"),
				"License of API",
				"API license URL",
				Collections.emptyList());
	}
}
