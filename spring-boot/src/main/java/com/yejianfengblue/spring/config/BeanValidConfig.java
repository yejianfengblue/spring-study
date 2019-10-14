package com.yejianfengblue.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 *
 * @author yejianfengblue
 *
 */
@Configuration
public class BeanValidConfig {

    /**
     * Configure a default Validator which support JSR-303 (bean validation 1.0),
     * JSR-349 (bean validation 1.1) and JSR-380 (bean validation 2.0)
     */
    @Bean
    public LocalValidatorFactoryBean validator() {

        return new LocalValidatorFactoryBean();
    }
}
