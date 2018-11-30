package com.fanduel;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fanduel.interceptor.SupportedSportInterceptor;

@EnableWebMvc
@Configuration
public class FanDuelWebConfig implements WebMvcConfigurer{
	
	@Override
	public void addInterceptors(final InterceptorRegistry registry) {
	    registry.addInterceptor(new SupportedSportInterceptor());
	}
}
