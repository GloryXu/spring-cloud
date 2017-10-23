package com.redsun.receiver;

import com.redsun.send.SinkSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * EnableBinding增加SinkSender接口的指定，让spring-cloud-stream能创建出对应的实例
 */
@EnableBinding(value = {Sink.class, SinkSender.class})
public class SinkReceiver {

    private static Logger logger = LoggerFactory.getLogger(SinkReceiver.class);

    @StreamListener(Sink.INPUT)
    public void receive(Object payload) {
        logger.info("Receive : " + payload);
    }

}
