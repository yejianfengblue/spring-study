package com.yejianfengblue.spring.amqp;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * @author yejianfengblue
 */
@Component
@Profile("rabbitmq-receiver")
public class RabbitmqReceiver {

    private Logger log = LoggerFactory.getLogger(getClass());

    @RabbitListener(queues = "#{autoDeleteQueue.name}")
    public void receive(String message) throws InterruptedException {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        log.info("[x] received '{}'", message);
        doWork(message);
        stopWatch.stop();
        log.info("[x] work done in {}s", stopWatch.getTotalTimeSeconds());
    }

    /**
     *  sleep for seconds as many as the char . in the income message
     */
    private void doWork(String message) throws InterruptedException {

        Thread.sleep(StringUtils.countMatches(message, '.') * 1000);
    }

    @Profile("rabbitmq-receiver")
    @Configuration
    public static class RabbitQueueConfig {

        @Bean
        public DirectExchange directExchange() {

            return new DirectExchange("my-channel-exchange");
        }

        @Bean
        public Queue autoDeleteQueue() {

            return new AnonymousQueue();
        }

        @Profile("bind-red")
        @Bean
        public Binding bindingRed(DirectExchange directExchange, Queue queue) {

            return BindingBuilder.bind(queue)
                    .to(directExchange)
                    .with(RoutingKey.RED);
        }

        @Profile("bind-green")
        @Bean
        public Binding bindingGreen(DirectExchange directExchange, Queue queue) {

            return BindingBuilder.bind(queue)
                    .to(directExchange)
                    .with(RoutingKey.GREEN);
        }

        @Profile("bind-blue")
        @Bean
        public Binding bindingBlue(DirectExchange directExchange, Queue queue) {

            return BindingBuilder.bind(queue)
                    .to(directExchange)
                    .with(RoutingKey.BLUE);
        }
    }
}
