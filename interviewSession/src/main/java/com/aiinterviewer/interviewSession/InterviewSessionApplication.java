package com.aiinterviewer.interviewSession;

import com.aiinterviewer.interviewSession.filter.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class InterviewSessionApplication {

	public static void main(String[] args) {
		SpringApplication.run(InterviewSessionApplication.class, args);
	}

	@Bean
	FilterRegistrationBean jwtFilterBean(){
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new JwtFilter());
		filterRegistrationBean.addUrlPatterns("/api/v2/interview/*");
		return filterRegistrationBean;
	}
}
