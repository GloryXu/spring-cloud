package com.redsun.send;


import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.MessageChannel;

public interface SinkSender {
    /**
     * 用于获得SinkSender接口中定义的MessageChannel实例
     *
     * @return
     */
    @Output(Sink.INPUT)
    MessageChannel output();
}
