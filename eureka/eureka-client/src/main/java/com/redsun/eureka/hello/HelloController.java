package com.redsun.eureka.hello;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private DiscoveryClient discoveryClient;

//    @RequestMapping(path = "/hello", method = RequestMethod.GET)
    @GetMapping("/hello")
    public String hello() {
        List<String> services = discoveryClient.getServices();

        for (String service : services) {
            List<ServiceInstance> serviceInstances = discoveryClient.getInstances(service);
            for (ServiceInstance serviceInstance : serviceInstances) {
                logger.info("/hello, host:" + serviceInstance.getHost() + ", port:" + serviceInstance.getPort() +
                        ", uri:" + serviceInstance.getUri());
            }
        }

        return "Hello world!";
    }
}
