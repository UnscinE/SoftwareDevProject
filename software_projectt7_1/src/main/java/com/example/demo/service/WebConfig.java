package com.example.demo.service;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // กำหนดเส้นทาง uploads ให้บริการไฟล์ในโฟลเดอร์ที่กำหนด
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:uploads/");
    }
}
