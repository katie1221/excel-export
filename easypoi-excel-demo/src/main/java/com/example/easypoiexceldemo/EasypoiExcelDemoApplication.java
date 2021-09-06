package com.example.easypoiexceldemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.easypoiexceldemo.dao")
public class EasypoiExcelDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasypoiExcelDemoApplication.class, args);
    }

}
