package com.yejianfengblue.spring.service;

import java.time.LocalDateTime;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class MyServiceImpl implements MyService {

    @Override
    public void print() {
        System.out.println("MyServiceImpl.print() at " + LocalDateTime.now().toString());
    }

    @PostConstruct
    public void postConstructPrint() {
        System.out.println("MyServiceImpl.postConstructPrint() at " + LocalDateTime.now().toString());
    }

    @PreDestroy
    public void preDestroyPrint() {
        System.out.println("MyServiceImpl.preDestroyPrint() at " + LocalDateTime.now().toString());
    }
}
