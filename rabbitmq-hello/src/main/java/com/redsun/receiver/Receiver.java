package com.redsun.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "hello")
public class Receiver {

    Logger logger = LoggerFactory.getLogger(Receiver.class);

    @RabbitHandler
    public void process(String hello) {
        logger.info("Receiver : " + hello);
    }
}
