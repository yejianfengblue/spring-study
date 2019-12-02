package com.yejianfengblue.spring.amqp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @author yejianfengblue
 */
@Component
@Profile("rabbitmq-receiver")
@RabbitListener(queues = "hello")
public class RabbitmqReceiver {

    private Logger log = LoggerFactory.getLogger(getClass());

    @RabbitHandler
    public void receive(String message) {

        log.info("[x] received '{}'", message);
    }

    @Configuration
    public static class RabbitQueueConfig {

        @Bean
        public Queue helloQueue() {

            return new Queue("hello");
        }
    }
}
