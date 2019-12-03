package com.yejianfengblue.spring.amqp;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yejianfengblue
 */
@Component
@Profile("rabbitmq-sender")
@RequiredArgsConstructor
public class RabbitmqSender {

    private final RabbitTemplate rabbitTemplate;

    private final String[] monsterCardRoutingKeys = {
            "normal.dark.spellcaster",
            "normal.dark.dragon",
            "normal.light.spellcaster",
            "normal.light.dragon",

            "effect.dark.spellcaster",
            "effect.dark.dragon",
            "effect.light.spellcaster",
            "effect.light.dragon",

            "fusion.dark.spellcaster",
            "fusion.dark.dragon",
            "fusion.light.spellcaster",
            "fusion.light.dragon"
    };

    private AtomicInteger messageCounter = new AtomicInteger(0);

    private AtomicInteger dotCounter = new AtomicInteger(0);

    private Logger log = LoggerFactory.getLogger(getClass());

    /**
     * Send "Hello" following a counter and a number of dots '.'
     */
    @Scheduled(fixedDelay = 5000, initialDelay = 5000)
    public void send() {

        StringBuilder messageBuilder = new StringBuilder("Hello");

        messageBuilder.append(messageCounter.incrementAndGet());

        dotCounter.incrementAndGet();
        for (int i = 0; i < (dotCounter.get()%10 + 1); i++) {
            messageBuilder.append('.');
        }

        String exchange = "ygo-exchange";
        String routingKey = monsterCardRoutingKeys[messageCounter.get()%12];
        messageBuilder.append(routingKey);
        String message = messageBuilder.toString();
        rabbitTemplate.convertAndSend(
                exchange,
                routingKey,
                message);
        log.info("[x] sent '{}' to exchange {} with routingKey {}",
                message,
                exchange,
                routingKey);
    }
}
