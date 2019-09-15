package com.yejianfengblue.spring.service;

import java.time.LocalDateTime;

public class MyServiceImpl implements MyService {

    @Override
    public void print() {
        System.out.println("MyServiceImpl.print() at " + LocalDateTime.now().toString());
    }
}
