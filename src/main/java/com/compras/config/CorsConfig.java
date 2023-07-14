package com.compras.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
		        registry.addMapping("/login")
		        		.allowedOrigins("http://localhost:4200")
//				        .allowedOrigins("http://listado-compras-front.s3-website-us-east-1.amazonaws.com")
				        .allowedMethods("*")
				        .exposedHeaders("*");
    	
		        registry.addMapping("/**")
		                .allowedOrigins("http://localhost:4200")
//		                .allowedOrigins("http://listado-compras-front.s3-website-us-east-1.amazonaws.com")
		                .allowedMethods("*");
			}
		};
	}
}