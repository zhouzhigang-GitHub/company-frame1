package com.zhou;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 北城
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.zhou.mapper"})
public class CompanyFrameApplication {

    public static void main(String[] args) {
        SpringApplication.run(CompanyFrameApplication.class, args);
    }

}
