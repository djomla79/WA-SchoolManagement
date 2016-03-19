package com.school.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages="com.school",
			   excludeFilters={@Filter(type=FilterType.ANNOTATION, value=EnableWebMvc.class)}
)
public class RootConfig {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new StandardPasswordEncoder();
	}
	
}
