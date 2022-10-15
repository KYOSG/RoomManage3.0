package com.sdut.rbs;

import com.sdut.rbs.dao.TimeOptionDao;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class RbsServerSfApplicationTests {

    @Resource
    private TimeOptionDao timeOptionDao;

    @Test
    public void timeOrderTest(){
        for (int i=0;i<7;i++){
            System.out.println(timeOptionDao.getAllTime());
        }
    }

}
