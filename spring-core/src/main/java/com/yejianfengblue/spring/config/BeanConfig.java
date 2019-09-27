package com.yejianfengblue.spring.config;

import com.yejianfengblue.spring.service.MyService;
import com.yejianfengblue.spring.service.MyServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public MyService myService() {
        return new MyServiceImpl();
    }
}
