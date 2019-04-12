package com.bob.wx_sell;

import com.bob.wx_sell.filter.CorsFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan(basePackages = "com.bob.wx_sell.dataobject.mapper")
@EnableCaching
public class WxSellApplication {

	public static void main(String[] args) {

		SpringApplication.run(WxSellApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new CorsFilter());
		registration.addUrlPatterns("/*");
		registration.addInitParameter("paramName", "paramValue");
		registration.setName("CorsFilter");
		registration.setOrder(1);
		return registration;
	}


}

