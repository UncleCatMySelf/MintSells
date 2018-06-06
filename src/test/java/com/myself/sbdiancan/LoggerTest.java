package com.myself.sbdiancan;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author:UncleCatMySelf
 * @Email：zhupeijie_java@126.com
 * @QQ:1341933031
 * @Date:Created in 16:24 2018\6\1 0001
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LoggerTest {

    //private final Logger logger = LoggerFactory.getLogger(LoggerTest.class);

    @Test
    public void test1(){
        String name = "myself";
        String password = "123456";
        log.debug("debug ...");
//        log.info("info .....");
        log.info("name:" + name + ",password:" + password);
        log.info("name:{},password:{}",name,password);
        log.error("error ...");
    }

}
