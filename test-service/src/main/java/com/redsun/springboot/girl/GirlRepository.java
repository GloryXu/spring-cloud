package com.redsun.springboot.girl;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by xugr on 2017/5/20.
 */
public interface GirlRepository extends JpaRepository<Girl, Integer>{

    /**
     * 通过年龄查询
     *
     * @param age
     * @return
     */
    List<Girl> findByAge(Integer age);
}
