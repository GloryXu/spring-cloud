package com.redsun.spring.boot.girl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by xugr on 2017/5/20.
 */
@RestController
public class GirlController {

    @Autowired
    private GirlRepository girlRepository;

    /**
     * 查询所有女生列表
     * @return
     */
    @GetMapping(value = "/girls")
    public List<Girl> girlList() {
        return girlRepository.findAll();
    }

    /**
     * 添加一个girl对象
     *
     * @param cupSize
     * @param age
     * @return
     */
    @PostMapping(value = "/girls")
    public Girl girlAdd(@RequestParam("cupSize")String cupSize, @RequestParam("age")Integer age) {
        Girl girl = new Girl();
        girl.setAge(age);
        girl.setCupSize(cupSize);

        return girlRepository.save(girl);
    }

    /**
     * 查询一个女生
     * @return
     */
    @GetMapping(value = "/girl/{id}")
    public Girl girlFindOn(@PathVariable("id") Integer id) {
        return girlRepository.getOne(id);
    }

//    @GetMapping(value = "/girl/{id}")
//
//    @GetMapping(value = "/girl/{id}")
}
