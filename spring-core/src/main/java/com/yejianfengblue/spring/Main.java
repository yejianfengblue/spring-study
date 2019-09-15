package com.yejianfengblue.spring;

import com.yejianfengblue.spring.config.AppConfig;
import com.yejianfengblue.spring.service.MyService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class Main {

    public static void main(String [] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        MyService myService = context.getBean(MyService.class);
        myService.print();
    }
}
