package com.redsun.eureka.hello;

import com.redsun.eureka.domain.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
public class HelloController {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    Registration eurekaRegistration;

    /*@GetMapping("/hello")
    public String hello() throws InterruptedException {
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(eurekaRegistration.getServiceId());

        // 让处理线程等待几秒钟
        int sleepTime = new Random().nextInt(3000);
        logger.info("sleep time : " + sleepTime);
        Thread.sleep(sleepTime);

        for (ServiceInstance serviceInstance : serviceInstances) {
            logger.info("/hello, host:" + serviceInstance.getHost() + ", port:" + serviceInstance.getPort() +
                    ", uri:" + serviceInstance.getUri() + ", serviceId:" + serviceInstance.getServiceId());
        }

        return "Hello world!";
    }*/

    @GetMapping("/hello")
    public String hello() throws InterruptedException {
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(eurekaRegistration.getServiceId());
        for (ServiceInstance serviceInstance : serviceInstances) {
            logger.info("/hello, host:" + serviceInstance.getHost() + ", port:" + serviceInstance.getPort() +
                    ", uri:" + serviceInstance.getUri() + ", serviceId:" + serviceInstance.getServiceId());
        }
        return "Hello world!";
    }

    @GetMapping("/hello1")
    public String hello(@RequestParam String name) throws InterruptedException {
        return "Hello world!" + name;
    }

    @GetMapping("/hello2")
    public User hello(@RequestHeader String name, @RequestHeader Integer age) throws InterruptedException {
        return new User(name, age);
    }

    @PostMapping("/hello3")
    public String hello(@RequestBody User user) throws InterruptedException {
        return "Hello " + user.getName() + "," + user.getAge() + ".";
    }
}
