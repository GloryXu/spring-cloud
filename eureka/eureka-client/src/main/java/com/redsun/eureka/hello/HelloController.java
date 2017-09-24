package com.redsun.eureka.hello;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
public class HelloController {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    Registration eurekaRegistration;

//    @RequestMapping(path = "/hello", method = RequestMethod.GET)
    @GetMapping("/hello")
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

        /*List<String> services = discoveryClient.getServices();
        for (String service : services) {
            List<ServiceInstance> serviceInstances = discoveryClient.getInstances(service);
            for (ServiceInstance serviceInstance : serviceInstances) {
                logger.info("/hello, host:" + serviceInstance.getHost() + ", port:" + serviceInstance.getPort() +
                        ", uri:" + serviceInstance.getUri());
            }
        }*/

        return "Hello world!";
    }
}
