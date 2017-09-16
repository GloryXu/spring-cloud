package com.redsun.springboot.girl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by xugr on 2017/5/21.
 */
@Service
public class GirlService {

    @Autowired
    private GirlRepository girlRepository;

    @Transactional
    public void insertTwo() {
        Girl girlA = new Girl();
        girlA.setCupSize("A");
        girlA.setAge(18);

        Girl girlB = new Girl();
        girlB.setCupSize("BBBBBBBB");
        girlB.setAge(18);


        girlRepository.save(girlA);
        girlRepository.save(girlB);
    }
}
