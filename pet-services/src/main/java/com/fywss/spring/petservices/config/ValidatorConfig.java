package com.fywss.spring.petservices.config;

import javax.validation.Validator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class ValidatorConfig {

	@Bean
	public Validator localValidatorFactoryBean() {
		return new LocalValidatorFactoryBean();
	}

}
