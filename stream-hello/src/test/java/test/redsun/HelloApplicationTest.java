package test.redsun;

import com.redsun.main.HelloStreamApplication;
import com.redsun.send.SinkSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = HelloStreamApplication.class)
public class HelloApplicationTest {

    @Autowired
    private SinkSender sinkSender;

    @Test
    public void contextLoads() {
        sinkSender.output().send(MessageBuilder.withPayload("From SinkSender").build());
    }

    @Autowired
    private MessageChannel input;

    @Test
    public void contextLoads1() {
        input.send(MessageBuilder.withPayload("From SinkSender").build());
    }
}
