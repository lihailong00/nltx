package com.lee.nltx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lee.nltx.mapper")
public class NltxApplication {

    public static void main(String[] args) {
        SpringApplication.run(NltxApplication.class, args);
    }

}
