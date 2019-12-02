package com.yejianfengblue.spring.amqp;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author yejianfengblue
 */
@Component
@Profile("rabbitmq-sender")
@RequiredArgsConstructor
public class RabbitmqSender {

    private final RabbitTemplate rabbitTemplate;

    private Logger log = LoggerFactory.getLogger(getClass());

    @Scheduled(fixedDelay = 5000, initialDelay = 5000)
    public void send() {

        String message = "Hello World!";
        rabbitTemplate.convertAndSend("hello", message);
        log.info("[x] sent '{}", message);
    }
}
