package com;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

@SpringBootApplication
public class Application extends WebMvcConfigurerAdapter {
	// 第一种方式配置使用FstJson
		@Override
		public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
	    	super.configureMessageConverters(converters);
			
	        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
	 
	        FastJsonConfig fastJsonConfig = new FastJsonConfig();
	        fastJsonConfig.setSerializerFeatures(
	                SerializerFeature.PrettyFormat
	        );
	        fastConverter.setFastJsonConfig(fastJsonConfig);
			
	    	converters.add(fastConverter);
		}
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
