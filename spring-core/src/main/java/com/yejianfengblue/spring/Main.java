package com.yejianfengblue.spring;

import com.yejianfengblue.spring.config.AppConfig;
import com.yejianfengblue.spring.service.MyService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class Main {

    public static void main(String [] args) {

        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        /*
        In a non-web application, registering a shutdown hook with JVM ensures a graceful shutdown
        and calls the relevant destroy methods on the singleton beans.
        https://docs.spring.io/spring-framework/docs/current/spring-framework-reference/core.html#beans-factory-shutdown
         */
        context.registerShutdownHook();
        MyService myService = context.getBean(MyService.class);
        myService.print();
    }
}
