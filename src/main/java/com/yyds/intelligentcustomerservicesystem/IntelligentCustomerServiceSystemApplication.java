package com.yyds.intelligentcustomerservicesystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yyds.intelligentcustomerservicesystem.mapper")
public class IntelligentCustomerServiceSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(IntelligentCustomerServiceSystemApplication.class, args);
    }

}
