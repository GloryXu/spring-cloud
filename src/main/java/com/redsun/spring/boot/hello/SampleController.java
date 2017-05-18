package com.redsun.spring.boot.hello;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xugr on 2017/5/18.
 */
@RestController
@EnableAutoConfiguration
public class SampleController {

    @RequestMapping("/hello")
    @ResponseBody
    String home() {
        return "Hello World!";
    }
}
