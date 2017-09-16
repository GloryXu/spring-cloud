package com.redsun.springboot.hello;

import com.redsun.springboot.properties.GirlProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by xugr on 2017/5/20.
 */
//@Controller
//@ResponseBody
@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private GirlProperties girlProperties;

//    http://localhost:8080/hello/say?id=100
//    @RequestMapping(value = "/say", method = RequestMethod.GET)
//    public String say1(@RequestParam(value = "id", required = false, defaultValue = "0") Integer id) {
//        return "id:" + id;
//    }


//    method 可缺失（get、post均可访问）
//    @RequestMapping(value = {"/say/{id}", "/hi"}, method = RequestMethod.GET)
    @GetMapping(value = "/say/{id}")
    public String say(@PathVariable("id") Integer id) {
//        return girlProperties.getCupSize();
//        return "index";
        return "id:" + id;
    }

}
