package com.zhou;

import com.zhou.service.RedisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {CompanyFrameApplication.class})
class CompanyFrameApplicationTests {

    @Test
    void contextLoads() {
    }


    @Autowired
    private RedisService redisService;
    @Test
    public void testRedis(){
        redisService.set("redis","lalalalala");
        System.out.println(redisService.get("redis"));
    }


}
