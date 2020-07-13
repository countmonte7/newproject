package com.newproject.web.config.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import javax.servlet.http.HttpServletRequest;

@Configuration
public class MessageConverterConfig extends MappingJackson2HttpMessageConverter{

    private final HttpServletRequest request;

    @Autowired
    public MessageConverterConfig(HttpServletRequest request) {
        this.request = request;
    }

}
