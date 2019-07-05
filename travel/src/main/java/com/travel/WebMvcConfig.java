package com.travel;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

  @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	  //なんだこれ
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:///home/ubuntu/app/uploads/");
    }
}

