package com.yejianfengblue.spring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class MyServiceImpl implements MyService {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void print() {
        log.info("MyServiceImpl.print() at " + LocalDateTime.now().toString());
    }

    @PostConstruct
    public void postConstructPrint() {
        log.trace("MyServiceImpl.postConstructPrint() at {}", LocalDateTime.now().toString());
    }

    @PreDestroy
    public void preDestroyPrint() {
        log.trace("MyServiceImpl.preDestroyPrint() at {}", LocalDateTime.now().toString());
    }
}
