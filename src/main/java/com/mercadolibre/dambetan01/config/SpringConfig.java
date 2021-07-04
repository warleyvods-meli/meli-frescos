package com.mercadolibre.dambetan01.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import com.mercadolibre.routing.RoutingFilter;
import java.text.SimpleDateFormat;
import java.util.TimeZone;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@ComponentScan("com.mercadolibre.dambetan01")
@Configuration
@EnableAutoConfiguration
public class SpringConfig implements WebMvcConfigurer {

	@Bean
	@Order(1)
	public RoutingFilter getRoutingFilter() {
		return new RoutingFilter();
	}

	private AfterburnerModule createAfterburnerModule() {
		AfterburnerModule afterburnerModule = new AfterburnerModule();

		afterburnerModule.setUseValueClassLoader(false);
		return afterburnerModule;
	}
}
