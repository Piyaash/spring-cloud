package com.example.demorestfulwebservices;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
public class DemoRestfulWebservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoRestfulWebservicesApplication.class, args);
	}

	@Bean
	public LocaleResolver localResolver() {
		AcceptHeaderLocaleResolver localeResolver=new AcceptHeaderLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}
	
//	@Bean
//	public MessageSource messageSource() {
//		ResourceBundleMessageSource messageSource= new ResourceBundleMessageSource();
//		messageSource.setBasename("messages");	
//		return messageSource;
//	}--- we need to add spring.messages.basename = messages in application.properties instead of writing this Bean(i.e: the method messageSource) 
	
}
