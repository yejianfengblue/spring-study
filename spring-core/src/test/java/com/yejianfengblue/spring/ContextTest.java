package com.yejianfengblue.spring;

import com.yejianfengblue.spring.config.AppConfig;
import com.yejianfengblue.spring.service.MyService;
import com.yejianfengblue.spring.service.MyServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author yejianfengblue
 */
@SpringJUnitConfig(AppConfig.class)
class ContextTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private MyService myService;

    @Test
    void givenAppContext_WhenInjected_ThenItShouldNotBeNull() {
        assertNotNull(applicationContext);
    }

    @Test
    void givenBeanDefined_WhenInjected_ThenItsClassShouldBeThatDefinedOne() {
        assertEquals(myService.getClass(), MyServiceImpl.class);
    }
}
