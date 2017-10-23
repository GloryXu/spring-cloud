package test.redsun;

import com.redsun.main.SpringIntegration;
import com.redsun.send.SinkSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringIntegration.class)
public class HelloApplicationTest {

    @Test
    public void contextLoads() {

    }

    @Test
    public void contextLoads1() {

    }
}
