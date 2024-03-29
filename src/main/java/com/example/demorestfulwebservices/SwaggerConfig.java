package com.example.demorestfulwebservices;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
  public static final ApiInfo DEFAULT_API_INFO=new ApiInfo("Title","Desc","1.0","urn:tos","","Apache url", null);
  
   private static final Set<String> DEFAULT_SET = new HashSet<String>(Arrays
		   .asList("application/json","application/xml"));
   
   
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(DEFAULT_API_INFO)
				.produces(DEFAULT_SET)
				.consumes(DEFAULT_SET);
	}
}
